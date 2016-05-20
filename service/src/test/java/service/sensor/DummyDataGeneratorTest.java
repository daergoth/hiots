package service.sensor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import net.daergoth.service.sensor.DummyDataGeneratorLocalImpl;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

public class DummyDataGeneratorTest {
	
	private static DummyDataGeneratorLocalImpl dummyGenerator;
	
	private static SensorContainerLocal sensorContainer;
	
	private static List<DummySensorVO> dummyList;
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		dummyGenerator = Mockito.spy(new DummyDataGeneratorLocalImpl());
		
		sensorContainer = Mockito.mock(SensorContainerLocal.class);
		
		dummyGenerator.setSensorContainer(sensorContainer);
		
		dummyList = new ArrayList<>();
		dummyList.add(new DummyTemperatureSensorVO(1, "DummyTemp", 10, 20, 1000));
		dummyList.add(new DummyLightSensorVO(2, "DummyLight", 100, 500, 1000));
		
		Mockito.when(sensorContainer.getDummySensors())
			.thenReturn(dummyList);
	}
	
	@Test
	public void testGenerateAllDummies() throws InvalidSensorDataTypeException {
		dummyGenerator.generateAllDummies();
		
		List<SensorDataVO> pastData = new ArrayList<>();
		for (DummySensorVO d : dummyList) {
			pastData.add(d.getData());
		}
		
		dummyGenerator.generateAllDummies();
		
		for (int i = 0; i < dummyList.size(); ++i) {
			if (dummyList.get(i).getData().compareTo(pastData.get(i)) == 0) {
				Assert.fail("Data wasn't generated!");
			}
		}
		
	}
}
