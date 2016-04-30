package net.daergoth.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.TabChangeEvent;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
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
	
	private List<SensorVO> sensors;
	
	private List<ActorVO> actors;
	
	private ConditionTypeService[] condTypes;
	
	private SensorType[] sensTypes;
	
	private int activeRuleIndex;
	
	@PostConstruct
	public void init() {
		setRules(ruleContainer.getRules());
		setSensors(sensorContainer.getSensors());
		setActors(actorContainer.getActors());
		setCondTypes(ConditionTypeService.values());
		setSensTypes(SensorType.values());
	}
	
	public void addExample() {
		RuleVO rule = new RuleVO();
		
		ActionVO action = new ActionVO();
		action.setId(2l);
		action.setActor(actorContainer.getActors().stream().filter(a -> a.getClass().equals(DummyThermostatActorVO.class)).findFirst().get());
		ThermostatActorStateVO state = new ThermostatActorStateVO();
		state.setTargetTemperature(35.0);
		action.setValue(state);
		rule.addAction(action);
		
		ConditionVO cond = new ConditionVO();
		cond.setId(2l);
		cond.setType(ConditionTypeService.GE);
		cond.setSensor(sensorContainer.getSensors().stream().filter(s -> s.getClass().equals(DummyTemperatureSensorVO.class)).findFirst().get());
		TemperatureDataVO data = new TemperatureDataVO(26.1);
		cond.setValue(data);
		rule.addCondition(cond);
		
		rule.setId(2l);
		rule.setName("Example Rule");
		rule.setEnabled(true);
		
		
		ruleContainer.addRule(rule);
	}
	
	public void onCellEditCondition(CellEditEvent event) throws InvalidSensorDataTypeException {
		System.out.println("----- CELLEDIT EVENT CONDITION -----");
		System.out.println("----- activeRuleIndex: " + activeRuleIndex + " -----");
		ConditionVO modifiedCond = rules.get(activeRuleIndex).getConditions().get(event.getRowIndex()); 
		switch (modifiedCond.getSensor().getType()) {
		case Light:
			modifiedCond.getValue().setType(SensorType.Light);
			break;
		case Temperature:
			modifiedCond.getValue().setType(SensorType.Temperature);
			break;
		default:
			throw new InvalidSensorDataTypeException("Unknown SensorType!");
			//break;
		}
		ruleContainer.updateRule(rules.get(activeRuleIndex));
	}
	
	public void onCellEditAction(CellEditEvent event) throws InvalidActorStateTypeException {
		System.out.println("----- CELLEDIT EVENT ACTION -----");
		System.out.println("----- activeRuleIndex: " + activeRuleIndex + " -----");
		ActionVO modifiedAct = rules.get(activeRuleIndex).getActions().get(event.getRowIndex());
		switch (modifiedAct.getActor().getType()) {
		case Lamp:
			modifiedAct.getValue().setType(ActorType.Lamp);
			break;
		case Thermostat:
			modifiedAct.getValue().setType(ActorType.Thermostat);
			break;
		default:
			throw new InvalidActorStateTypeException("Unknown ActorType");
		
		}
		ruleContainer.updateRule(rules.get(activeRuleIndex));
	}
	
	public void onTabChange(TabChangeEvent event) {
		System.out.println("----- activeRuleIndex: " + activeRuleIndex + " -----");
	}
	
	public void deleteRule(String id) {
		ruleContainer.deleteRule(Long.parseLong(id));
	}

	public List<RuleVO> getRules() {
		return rules;
	}

	public void setRules(List<RuleVO> rules) {
		this.rules = rules;
	}

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}

	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}

	public ConditionTypeService[] getCondTypes() {
		return condTypes;
	}

	public void setCondTypes(ConditionTypeService[] condTypes) {
		this.condTypes = condTypes;
	}

	public SensorType[] getSensTypes() {
		return sensTypes;
	}

	public void setSensTypes(SensorType[] sensType) {
		this.sensTypes = sensType;
	}

	public int getActiveRuleIndex() {
		return activeRuleIndex;
	}

	public void setActiveRuleIndex(int activeRuleIndex) {
		this.activeRuleIndex = activeRuleIndex;
	}

	
	
	

}
