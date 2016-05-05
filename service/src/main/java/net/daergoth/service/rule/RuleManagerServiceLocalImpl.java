package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;

import net.daergoth.coreapi.rule.RuleDaoLocal;
import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.InvalidConditionTypeException;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

@Singleton
@Local(RuleManagerServiceLocal.class)
public class RuleManagerServiceLocalImpl implements RuleManagerServiceLocal{
	
	@EJB
	RuleDaoLocal ruleDao;
	
	@EJB
	DataChangeListenerLocal changeListener;
	
	List<RuleVO> rules = new ArrayList<>();	
	
	Map<Long, List<DataChangeHandler>> handlers = new HashMap<>();
	
	private boolean changed = true;

	@Override
	public List<RuleVO> getRules() {
		if (changed) {
			rules = RuleConverter.toVOs(ruleDao.getRules());
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
			changeListener.subscribeFor(cond.getSensor(), h);
		}
		
		ruleDao.addRule(RuleConverter.toDTO(r));
	}

	@Override
	public void updateRule(RuleVO r) {
		changed = true;
		
		for (ConditionVO cond : r.getConditions()) {
			changeListener.unsubscribeFrom(cond.getSensor(), handlers.get(r.getId()));
		}
		
		List<DataChangeHandler> handlerList = new ArrayList<>();
		for (ConditionVO cond : r.getConditions()) {
			DataChangeHandler h = handlerFromCondition(cond, r.getId());
			handlerList.add(h);
			changeListener.subscribeFor(cond.getSensor(), h);
		}
		handlers.put(r.getId(), handlerList);
		
		ruleDao.updateRule(RuleConverter.toDTO(r));
	}

	@Override
	public void deleteRule(Long id) {
		changed = true;
		
		RuleVO rule = rules.stream().filter(r -> r.getId() == id).findFirst().get();
		for (ConditionVO cond : rule.getConditions()) {
			changeListener.unsubscribeFrom(cond.getSensor(), handlers.get(id));
		}
		handlers.remove(id);
		
		ruleDao.deleteRule(id);
	}
	
	public void checkForRule(Long ruleId) {
		System.out.println("CheckForRule id: " + ruleId);
		
		RuleVO rule = rules.stream().filter(r -> r.getId() == ruleId).findFirst().get();
		boolean result = true;
		for (ConditionVO cond : rule.getConditions()) {
			try {
				result = result && evaluateCondition(cond, cond.getSensor().getData());
				
			} catch (InvalidSensorDataTypeException | InvalidConditionTypeException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("CheckForRule result: " + result);
		
		if (result) {
			for (ActionVO action : rule.getActions()) {
				try {
					System.out.println("CheckForRule action: " + action.getActor() + " -> " + action.getValue());
					action.getActor().setState(action.getValue());
				} catch (InvalidActorStateTypeException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean evaluateCondition(ConditionVO cond, SensorDataVO data) throws InvalidSensorDataTypeException, InvalidConditionTypeException  {
		/*System.out.println(data);
		System.out.println(cond.getType());
		System.out.println(cond.getValue());*/
		switch (cond.getType()) {
		case EQ:
			System.out.println(data.compareTo(cond.getValue()) == 0);
			return data.compareTo(cond.getValue()) == 0;
			//break;
		case GE:
			System.out.println(data.compareTo(cond.getValue()) >= 0);
			return data.compareTo(cond.getValue()) >= 0;
			//break;
		case GT:
			System.out.println(data.compareTo(cond.getValue()) > 0);
			return data.compareTo(cond.getValue()) > 0;
			//break;
		case LE:
			System.out.println(data.compareTo(cond.getValue()) <= 0);
			return data.compareTo(cond.getValue()) <= 0;
			//break;
		case LT:
			System.out.println(data.compareTo(cond.getValue()) < 0);
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
				try {
					if (evaluateCondition(cond, newData)) {
						checkForRule(ruleId);
					}
				} catch (InvalidSensorDataTypeException e) {
					e.printStackTrace();
				} catch (InvalidConditionTypeException e) {
					e.printStackTrace();
				}
			}
		};
	}

}
