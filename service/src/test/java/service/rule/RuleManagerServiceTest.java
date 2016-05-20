package service.rule;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import net.daergoth.service.actor.ActorContainerLocalImpl;
import net.daergoth.service.rule.RuleManagerServiceLocalImpl;
import net.daergoth.service.sensor.SensorContainerLocalImpl;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.InvalidConditionTypeException;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class RuleManagerServiceTest {
	
	private static SensorVO sensor;
	private static ConditionVO cond;
	private static SensorDataVO data;
	private static SensorContainerLocalImpl sensorContainerLocalImpl;
	private static ActorContainerLocalImpl actorContainerLocalImpl;
	private static RuleManagerServiceLocalImpl rulesManager;
	
	private static List<SensorVO> sensors;
	
	private static List<ActorVO> actors;

	@BeforeClass
	public static void setUpBeforeClass() throws InvalidSensorDataTypeException, InvalidActorStateTypeException {
		sensor = new TemperatureSensorVO(2l, "TempSensor");
		
		cond = new ConditionVO();
		cond.setId(1l);
		cond.setSensor(sensor);
		cond.setValue(new TemperatureDataVO(23.4));
		
		data = new TemperatureDataVO(33.4);
		
		rulesManager = Mockito.spy(new RuleManagerServiceLocalImpl());
		sensorContainerLocalImpl = Mockito.mock(SensorContainerLocalImpl.class);
		actorContainerLocalImpl = Mockito.mock(ActorContainerLocalImpl.class);
		
		TemperatureSensorVO temperature = new TemperatureSensorVO(1, "TestTemp");
		temperature.setData(new TemperatureDataVO(23.4));
		
		LightSensorVO light = new LightSensorVO(2, "TestLight");
		light.setData(new LightDataVO(100));
		
		sensors = Arrays.asList(temperature, light);
		
		LampActorVO lamp = new LampActorVO(3, "TestLamp");
		LampActorStateVO ldata = new LampActorStateVO();
		ldata.setStatus(true);
		lamp.setState(ldata);
		
		actors = Arrays.asList(lamp);
		
		ConditionVO ruleCond1 = new ConditionVO();
		ruleCond1.setId(1l);
		ruleCond1.setType(ConditionTypeService.LE);
		ruleCond1.setSensor(temperature);
		ruleCond1.setValue(new TemperatureDataVO(33.4));
		
		ConditionVO ruleCond2 = new ConditionVO();
		ruleCond2.setId(2l);
		ruleCond2.setType(ConditionTypeService.GE);
		ruleCond2.setSensor(light);
		ruleCond2.setValue(new LightDataVO(54));
		
		LampActorStateVO ruleActionValue = new LampActorStateVO();
		ruleActionValue.setData(0.0);
		
		ActionVO ruleAction = new ActionVO();
		ruleAction.setId(1l);
		ruleAction.setActor(lamp);
		ruleAction.setValue(ruleActionValue);
		
		RuleVO rule = new RuleVO();
		rule.setConditions(Arrays.asList(ruleCond1, ruleCond2));
		
		rule.setActions(Arrays.asList(ruleAction));
		
		rule.setId(1l);
		rule.setName("TestRule");
		rule.setEnabled(true);
		
		rulesManager.setRules(Arrays.asList(rule));
		
		rulesManager.setSensorContainer(sensorContainerLocalImpl);
		
		rulesManager.setActorContainer(actorContainerLocalImpl);
		
		Mockito.when(sensorContainerLocalImpl.getSensors())
			.thenReturn(sensors);
		
		Mockito.when(actorContainerLocalImpl.getActors())
			.thenReturn(actors);
		
	}

	
	private void assertEvaluateCondition(double d) throws InvalidSensorDataTypeException, InvalidConditionTypeException {
		double condvalue = cond.getValue().getData();
		data.setData(d);
		
		cond.setType(ConditionTypeService.EQ);
		Assert.assertEquals(d == condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.GE);
		Assert.assertEquals(d >= condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.GT);
		Assert.assertEquals(d > condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.LE);
		Assert.assertEquals(d <= condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.LT);
		Assert.assertEquals(d < condvalue, rulesManager.evaluateCondition(cond, data));
	}

	@Test
	public void testEvaluateCondition() throws InvalidSensorDataTypeException, InvalidConditionTypeException {
		assertEvaluateCondition(33.4);
		
		assertEvaluateCondition(23.4);
		
		assertEvaluateCondition(13.4);
	}
	
	@Test
	public void testCheckForRule() throws InvalidSensorDataTypeException, InvalidConditionTypeException {
		
		rulesManager.checkForRule(1l);
		Assert.assertEquals(0.0, actors.get(0).getState().getData(), 0.0);
		
		rulesManager.checkForRule(1l);
		Assert.assertEquals(0.0, actors.get(0).getState().getData(), 0.0);
		
		LampActorStateVO onState = new LampActorStateVO();
		onState.setStatus(true);
		rulesManager.getRules().get(0).getActions().get(0).setValue(onState);
		rulesManager.getRules().get(0).setEnabled(false);
		rulesManager.checkForRule(1l);
		Assert.assertEquals(0.0, actors.get(0).getState().getData(), 0.0);
		
		rulesManager.getRules().get(0).getConditions().get(0).setType(ConditionTypeService.GT);
		rulesManager.getRules().get(0).setEnabled(true);
		rulesManager.checkForRule(1l);
		Assert.assertEquals(0.0, actors.get(0).getState().getData(), 0.0);
	}
	
	

}
