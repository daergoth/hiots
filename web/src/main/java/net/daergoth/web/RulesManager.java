package net.daergoth.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@ManagedBean(name = "rulesManager")
@ViewScoped
public class RulesManager {
	
	@EJB
	RuleManagerServiceLocal ruleContainer;
	
	@EJB
	ActorContainerLocal actorContainer;
	
	@EJB
	SensorContainerLocal sensorContainer;
	
	private List<RuleVO> rules;
	
	@PostConstruct
	public void init() {
		setRules(ruleContainer.getRules());
		
	}
	
	public void addExample() {
		RuleVO rule = new RuleVO();
		
		ActionVO action = new ActionVO();
		action.setId(1l);
		action.setActor(actorContainer.getActors().stream().filter(a -> a.getClass().equals(DummyThermostatActorVO.class)).findFirst().get());
		ThermostatActorStateVO state = new ThermostatActorStateVO();
		state.setTargetTemperature(35.0);
		action.setValue(state);
		rule.addAction(action);
		
		ConditionVO cond = new ConditionVO();
		cond.setId(1l);
		cond.setType(ConditionTypeService.GE);
		cond.setSensor(sensorContainer.getSensors().stream().filter(s -> s.getClass().equals(DummyTemperatureSensorVO.class)).findFirst().get());
		TemperatureDataVO data = new TemperatureDataVO(26.1);
		cond.setValue(data);
		rule.addCondition(cond);
		
		rule.setId(1l);
		rule.setName("Example Rule");
		rule.setEnabled(true);
		
		
		ruleContainer.addRule(rule);
	}

	public List<RuleVO> getRules() {
		return rules;
	}

	public void setRules(List<RuleVO> rules) {
		this.rules = rules;
	}
	
	

}
