package net.daergoth.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;
import net.daergoth.serviceapi.changelistener.DataChangeListenerLocal;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

@ManagedBean(name = "rulesManager")
@ViewScoped
public class RulesManager {
	
	@EJB
	private RuleManagerServiceLocal ruleContainer;
	
	@EJB
	private ActorContainerLocal actorContainer;
	
	@EJB
	private SensorContainerLocal sensorContainer;

	@EJB
	private DataChangeListenerLocal changeListener;
	
	private List<RuleVO> rules;
	private List<SensorVO> sensors;
	private List<ActorVO> actors;
	
	private ConditionTypeService[] condTypes;
	private SensorType[] sensTypes;
	
	private int activeRuleIndex;
	
	private SensorVO newCondSensor;
	private ConditionTypeService newCondType;
	private String newCondValue;
	
	private ActorVO newActActor;
	private String newActValue;
	
	private String newRuleName;
	
	private String newName;
	
	@PostConstruct
	public void init() {
		setRules(ruleContainer.getRules());
		setSensors(sensorContainer.getSensors());
		setActors(actorContainer.getActors());
		setCondTypes(ConditionTypeService.values());
		setSensTypes(SensorType.values());
		
		if (!sensors.isEmpty()) {
			newCondSensor = sensors.get(0);
		}
		if (!actors.isEmpty()) {
			newActActor = actors.get(0);
		}
	}
	
	public void addCondition() {
		ConditionVO cond = new ConditionVO();
		cond.setId(0l);
		cond.setSensor(newCondSensor);
		cond.setType(newCondType);
		SensorDataVO val;
		switch (newCondSensor.getType()) {
		case Light:
			val = new LightDataVO(Double.parseDouble(newCondValue));
			break;
		case Temperature:
			val = new TemperatureDataVO(Double.parseDouble(newCondValue));
			break;
		default:
			val = null;
			break;
		}
		val.setType(newCondSensor.getType());
		cond.setValue(val);
		
		rules.get(activeRuleIndex).addCondition(cond);
		ruleContainer.updateRule(rules.get(activeRuleIndex));
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('condDlg').hide();");
	}
	
	public void removeCondition(String id) {
		rules.get(activeRuleIndex).deleteCondition(
				rules.get(activeRuleIndex).getConditions().stream().filter(c -> c.getId() == Long.parseLong(id)).findFirst().get()
		);
	}
	
	public void addAction() {
		
		ActionVO action = new ActionVO();
		action.setId(0l);
		action.setActor(newActActor);
		ActorStateVO state;
		switch(newActActor.getType()) {
		case Lamp:
			state = new LampActorStateVO();
			state.setData(Double.parseDouble(newActValue));
			break;
		case Thermostat:
			state = new ThermostatActorStateVO();
			state.setData(Double.parseDouble(newActValue));
			break;
		default:
			state = null;
			break;
		}
		state.setType(newActActor.getType());
		action.setValue(state);
		
		rules.get(activeRuleIndex).addAction(action);
		ruleContainer.updateRule(rules.get(activeRuleIndex));
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('actDlg').hide();");
	}
	
	public void removeAction(String id) {
		rules.get(activeRuleIndex).deleteAction(
				rules.get(activeRuleIndex).getActions().stream().filter(a -> a.getId() == Long.parseLong(id)).findFirst().get()
		);
	}
	
	public void deleteRule() {
		System.out.println("ActiveRuleIndex:_" + activeRuleIndex);
		ruleContainer.deleteRule(rules.get(activeRuleIndex).getId());
		setRules(ruleContainer.getRules());
	}
	
	public void renameRule()  {
		System.out.println("ActiveRuleIndex:_" + activeRuleIndex);
		rules.get(activeRuleIndex).setName(newName);
		newName = "";
		ruleContainer.updateRule(rules.get(activeRuleIndex));
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('renameDlg').hide();");
	}
	
	public void setRuleEnable() {
		ruleContainer.updateRule(rules.get(activeRuleIndex));
	}
	
	public void addRule() {
		RuleVO rule = new RuleVO();
		rule.setId(0l);
		rule.setName(newRuleName);
		rule.setEnabled(true);
		
		ruleContainer.addRule(rule);
		setRules(ruleContainer.getRules());
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('newRuleDlg').hide();");
	}
	
	public void onCellEditCondition(CellEditEvent event) throws InvalidSensorDataTypeException {
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
			//break;
		}
		ruleContainer.updateRule(rules.get(activeRuleIndex));
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

	public SensorVO getNewCondSensor() {
		return newCondSensor;
	}

	public void setNewCondSensor(SensorVO newCondSensor) {
		this.newCondSensor = newCondSensor;
	}

	public ConditionTypeService getNewCondType() {
		return newCondType;
	}

	public void setNewCondType(ConditionTypeService newCondType) {
		this.newCondType = newCondType;
	}

	public String getNewCondValue() {
		return newCondValue;
	}

	public String getNewRuleName() {
		return newRuleName;
	}

	public void setNewRuleName(String newRuleName) {
		this.newRuleName = newRuleName;
	}

	public ActorVO getNewActActor() {
		return newActActor;
	}

	public void setNewActActor(ActorVO newActActor) {
		this.newActActor = newActActor;
	}

	public String getNewActValue() {
		return newActValue;
	}

	public void setNewActValue(String newActValue) {
		this.newActValue = newActValue;
	}

	public void setNewCondValue(String newCondValue) {
		this.newCondValue = newCondValue;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

}
