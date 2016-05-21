package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.daergoth.coreapi.rule.RuleDaoLocal;
import net.daergoth.service.cobertura.CoverageIgnore;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.changelistener.DataChangeHandler;
import net.daergoth.serviceapi.changelistener.DataChangeListenerLocal;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.InvalidConditionTypeException;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * Default implementation of {@code RuleManagerServiceLocal}.
 */
@Singleton
@Startup
@DependsOn({"DataChangeListener","ActorContainer"})
@Local(RuleManagerServiceLocal.class)
public class RuleManagerServiceLocalImpl implements RuleManagerServiceLocal{
	
	private static final Logger logger = LoggerFactory.getLogger(RuleManagerServiceLocal.class);
	
	@EJB
	private RuleDaoLocal ruleDao;
	
	@EJB
	private DataChangeListenerLocal changeListener;
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	@EJB
	private ActorContainerLocal actorContainer;
	
	private List<RuleVO> rules = new ArrayList<>();	
	
	private Map<Long, List<DataChangeHandler>> handlers = new HashMap<>();
	
	private boolean changed = true;
	
	@PostConstruct
	private void init() {
		for (RuleVO rule : getRules()) {
			if (!handlers.containsKey(rule.getId())) {
				handlers.put(rule.getId(), new ArrayList<>());
			}
			
			for (ConditionVO cond : rule.getConditions()) {
				DataChangeHandler h = handlerFromCondition(cond, rule.getId());
				handlers.get(rule.getId()).add(h);
				changeListener.subscribeFor(cond.getSensor().getId(), h);
			}
		} 
		logger.info("Service initialized!");
	}
	
	@PreDestroy
	private void destroy() {
		for (RuleVO rule : getRules()) {
			for (ConditionVO cond : rule.getConditions()) {
				changeListener.unsubscribeFrom(cond.getSensor().getId(), handlers.get(rule.getId()));
			}
		}
		logger.info("Service destroyed!");
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public List<RuleVO> getRules() {
		if (changed) {
			try {
				rules = RuleConverter.toVOs(ruleDao.getRules());
			} catch (SensorConvertException | ActorConvertException e) {
				logger.error("Error during Actor/Sensor converting related to a Rule.", e);
			}
			changed = false;
		}
		
		return rules;
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void addRule(RuleVO r) {
		changed = true;
		
		if (!handlers.containsKey(r.getId())) {
			handlers.put(r.getId(), new ArrayList<>());
		}
		for (ConditionVO cond : r.getConditions()) {
			DataChangeHandler h = handlerFromCondition(cond, r.getId());
			handlers.get(r.getId()).add(h);
			changeListener.subscribeFor(cond.getSensor().getId(), h);
		}
		
		ruleDao.addRule(RuleConverter.toDTO(r));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void updateRule(RuleVO r) {
		changed = true;
		
		for (ConditionVO cond : r.getConditions()) {
			changeListener.unsubscribeFrom(cond.getSensor().getId(), handlers.get(r.getId()));
		}
		
		List<DataChangeHandler> handlerList = new ArrayList<>();
		for (ConditionVO cond : r.getConditions()) {
			DataChangeHandler h = handlerFromCondition(cond, r.getId());
			handlerList.add(h);
			changeListener.subscribeFor(cond.getSensor().getId(), h);
		}
		handlers.put(r.getId(), handlerList);
		
		ruleDao.updateRule(RuleConverter.toDTO(r));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void deleteRule(Long id) {
		changed = true;
		
		RuleVO rule = rules.stream().filter(r -> r.getId() == id).findFirst().get();
		for (ConditionVO cond : rule.getConditions()) {
			changeListener.unsubscribeFrom(cond.getSensor().getId(), handlers.get(id));
		}
		handlers.remove(id);
		
		ruleDao.deleteRule(id);
	}
	
	/**
	 * Checks if a {@code Rule}'s conditions are all met.
	 * @param ruleId  the ID of the rule
	 */
	public void checkForRule(Long ruleId) {
		logger.info("Checking for changes in Rule(id:{})...", ruleId);
		
		RuleVO rule = rules.stream().filter(r -> r.getId() == ruleId).findFirst().get(); 
		
		boolean result = true;
		for (ConditionVO cond : rule.getConditions()) {
			SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == cond.getSensor().getId()).findFirst().get();
			try {
				result = result && evaluateCondition(cond, sensor.getData());
				
			} catch (InvalidSensorDataTypeException | InvalidConditionTypeException e) {
				e.printStackTrace();
			}
		}
		
		logger.debug("Rule(id:{}) conditions result: {}", ruleId, result);
		
		if (result && rule.isEnabled()) {
			for (ActionVO action : rule.getActions()) {
				try {
					ActorVO actor = actorContainer.getActors().stream().filter(a -> a.getId() == action.getActor().getId()).findFirst().get();
					
					if (actor.getState() != action.getValue()) {
						logger.debug("Rule(id:"+ruleId+") action: {} -> {}", actor, action.getValue() );
						actor.setState(action.getValue());
					}
				} catch (InvalidActorStateTypeException e) {
					logger.error("Rule(id:" + ruleId + ") action has wrong value!", e);
				}
			}
		}
	}
	
	/**
	 * Evaluates a {@code Condition}, checking it against a sensor reading.
	 * @param cond  the condition to check
	 * @param data  the sensor reading to check against
	 * @return true if the condition given is fulfilled
	 * @throws InvalidSensorDataTypeException if the reading data's type and the condition's sensor's type doesn't match
	 * @throws InvalidConditionTypeException if the condition's type is invalid
	 */
	public boolean evaluateCondition(ConditionVO cond, SensorDataVO data) throws InvalidSensorDataTypeException, InvalidConditionTypeException  {
		logger.info("Evaluating condition...");
		logger.debug("SensorData: {}", data);
		logger.debug("Condition type: {}", cond.getType());
		logger.debug("Condition value: {}", cond.getValue());
		
		switch (cond.getType()) {
		case EQ:
			return data.compareTo(cond.getValue()) == 0;
			//break;
		case GE:
			return data.compareTo(cond.getValue()) >= 0;
			//break;
		case GT:
			return data.compareTo(cond.getValue()) > 0;
			//break;
		case LE:
			return data.compareTo(cond.getValue()) <= 0;
			//break;
		case LT:
			return data.compareTo(cond.getValue()) < 0;
			//break;
		default:
			throw new InvalidConditionTypeException("Invalid ConditionType!");
			//break;
		}
	}
	
	private DataChangeHandler handlerFromCondition(ConditionVO cond, Long ruleId) {
		
		return new DataChangeHandler() {
			
			@Override
			public void onChange(SensorDataVO newData) {
				logger.debug("DataChangeHandler for Rule(id:{}) executed.", ruleId);
				try {
					if (evaluateCondition(cond, newData)) {
						checkForRule(ruleId);
					}
				} catch (InvalidSensorDataTypeException | InvalidConditionTypeException e) {
					logger.error("Condition(id:" +  cond.getId() + ", ruleId:" + ruleId + ")'s value and sensor type doesn't match!", e);
				}
			}
		};
	}

	/**
	 * Setter for the {@code SensorContainerLocal} service.
	 * @param sensorContainer  the sensor-provider service
	 */
	public void setSensorContainer(SensorContainerLocal sensorContainer) {
		this.sensorContainer = sensorContainer;
	}

	/**
	 * Setter for the {@code ActorContainerLocal} service.
	 * @param actorContainer  the actor-provider service
	 */
	public void setActorContainer(ActorContainerLocal actorContainer) {
		this.actorContainer = actorContainer;
	}

	/**
	 * Setter for the managed list of {@code Rule}s.
	 * @param rules  the list of managed rules
	 */
	public void setRules(List<RuleVO> rules) {
		this.rules = rules;
		changed = false;
	}
	
	

}
