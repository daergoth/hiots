package service.sensor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.SessionContext;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import net.daergoth.service.sensor.DummyDataGeneratorLocalImpl;
import net.daergoth.serviceapi.sensors.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

public class DummyDataGeneratorTest {
	
	private static DummyDataGeneratorLocalImpl dummyGenerator;
	
	private static SensorContainerLocal sensorContainer;
	private static Timer tm;
	private static TimerService timerService;
	private static SessionContext ctx;
	
	private static List<DummySensorVO> dummyList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dummyGenerator = new DummyDataGeneratorLocalImpl();
		
		sensorContainer = Mockito.mock(SensorContainerLocal.class);
		tm = Mockito.mock(Timer.class);
		timerService = Mockito.mock(TimerService.class);
		ctx = Mockito.mock(SessionContext.class);
		
		dummyGenerator.setSensorContainer(sensorContainer);
		
		dummyList = new ArrayList<>();
		dummyList.add(new DummyTemperatureSensorVO(1, "DummyTemp", 10, 20, 1000));
		dummyList.add(new DummyLightSensorVO(2, "DummyLight", 100, 500, 1000));
	}

	@Test
	public void testStartStopGenerating() {
		/*Mockito.when(sensorContainer.getDummySensors())
			.thenReturn(dummyList);
		
		Mockito.when(timerService.createIntervalTimer(Mockito.any(), Mockito.any(), Mockito.any() ))
			.thenReturn(tm);
		
		Mockito.when(ctx.getTimerService())
			.thenReturn(timerService);*/
		
		
		Assert.assertEquals(false, dummyGenerator.isGenerating());
		
		dummyGenerator.startGenerating();
		
		Assert.assertEquals(true, dummyGenerator.isGenerating());
		
		dummyGenerator.stopGenerating();
		
		Assert.assertEquals(false, dummyGenerator.isGenerating());
	}

	@Test
	public void testGenerateAllDummies() {
		Assert.fail("Not yet implemented");
	}

}
