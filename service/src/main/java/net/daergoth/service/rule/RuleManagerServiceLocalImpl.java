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

import net.daergoth.coreapi.rule.RuleDaoLocal;
import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
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

@Singleton
@Startup
@DependsOn({"DataChangeListener","ActorContainer"})
@Local(RuleManagerServiceLocal.class)
public class RuleManagerServiceLocalImpl implements RuleManagerServiceLocal{
	
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
	public void init() {
		System.out.println("RuleManagerService @PostConstruct");
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
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("RuleManagerService @PreDestroy");
		for (RuleVO rule : getRules()) {
			for (ConditionVO cond : rule.getConditions()) {
				changeListener.unsubscribeFrom(cond.getSensor().getId(), handlers.get(rule.getId()));
			}
		}
	}

	@Override
	public List<RuleVO> getRules() {
		if (changed) {
			try {
				rules = RuleConverter.toVOs(ruleDao.getRules());
			} catch (SensorConvertException | ActorConvertException e) {
				e.printStackTrace();
			}
			changed = false;
		}
		
		return rules;
	}

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
	
	public void checkForRule(Long ruleId) {
		System.out.println("RuleManagerService checkForRule id: " + ruleId);
		
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
		
		System.out.println("RuleManagerService checkForRule result: " + result);
		
		if (result) {
			for (ActionVO action : rule.getActions()) {
				try {
					ActorVO actor = actorContainer.getActors().stream().filter(a -> a.getId() == action.getActor().getId()).findFirst().get();
					System.out.println("RuleManagerService checkForRule (actor.state, action.value): " + actor.getState() + ", " + action.getValue());
					//System.out.println("RuleManagerService checkForRule action.actor.state != action.value: " + (action.getActor().getState() != action.getValue()) );
					if (actor.getState() != action.getValue()) {
						System.out.println("RuleManagerService checkForRule action: " + actor + " -> " + action.getValue());
						actor.setState(action.getValue());
					}
				} catch (InvalidActorStateTypeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean evaluateCondition(ConditionVO cond, SensorDataVO data) throws InvalidSensorDataTypeException, InvalidConditionTypeException  {
		/*
		System.out.println("RuleManagerService evaluateCondition sensorData: " + data);
		System.out.println("RuleManagerService evaluateCondition condType: " + cond.getType());
		System.out.println("RuleManagerService evaluateCondition condValue: " + cond.getValue());
		*/
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
			public void onChange(SensorDataVO newData) throws InvalidSensorDataTypeException, InvalidConditionTypeException {
				if (evaluateCondition(cond, newData)) {
					checkForRule(ruleId);
				}
			}
		};
	}

}
