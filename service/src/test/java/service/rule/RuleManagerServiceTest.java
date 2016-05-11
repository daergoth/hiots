package service.rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import net.daergoth.coreapi.rule.RuleDaoLocal;
import net.daergoth.service.rule.RuleManagerServiceLocalImpl;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.InvalidConditionTypeException;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class RuleManagerServiceTest {
	
	static SensorVO sensor;
	static ConditionVO cond;
	static SensorDataVO data;
	
	static RuleManagerServiceLocalImpl rulesManager;
	static RuleDaoLocal ruleDao;

	@BeforeClass
	public static void setUpBeforeClass() throws InvalidSensorDataTypeException {
		sensor = new TemperatureSensorVO(2l, "TempSensor");
		
		cond = new ConditionVO();
		cond.setId(1l);
		cond.setSensor(sensor);
		cond.setValue(new TemperatureDataVO(23.4));
		
		data = new TemperatureDataVO(33.4);
		
		rulesManager = Mockito.spy(new RuleManagerServiceLocalImpl());
		ruleDao = Mockito.mock(RuleDaoLocal.class);
	}

	@Test
	public void testCheckForRule() {
		fail("Not yet implemented");
	}
	
	private void assertEvaluateCondition(double d) throws InvalidSensorDataTypeException, InvalidConditionTypeException {
		double condvalue = cond.getValue().getData();
		data.setData(d);
		
		cond.setType(ConditionTypeService.EQ);
		assertEquals(d == condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.GE);
		assertEquals(d >= condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.GT);
		assertEquals(d > condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.LE);
		assertEquals(d <= condvalue, rulesManager.evaluateCondition(cond, data));
		
		cond.setType(ConditionTypeService.LT);
		assertEquals(d < condvalue, rulesManager.evaluateCondition(cond, data));
	}

	@Test
	public void testEvaluateCondition() throws InvalidSensorDataTypeException, InvalidConditionTypeException {
		assertEvaluateCondition(33.4);
		
		assertEvaluateCondition(23.4);
		
		assertEvaluateCondition(13.4);
	}

}
