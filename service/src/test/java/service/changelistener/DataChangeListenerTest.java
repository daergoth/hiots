package service.changelistener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import net.daergoth.service.changelistener.DataChangeListenerLocalImpl;
import net.daergoth.service.sensor.SensorContainerLocalImpl;
import net.daergoth.serviceapi.changelistener.DataChangeHandler;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class DataChangeListenerTest {

	private static DataChangeListenerLocalImpl changeListener;
	
	private static SensorContainerLocalImpl sensorContainer;
	
	private static List<SensorVO> sensors;
	
	private static List<DataChangeHandler> handlers;
	
	private static List<Integer> handlerTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		handlerTest = new ArrayList<>();
		
		sensorContainer = Mockito.mock(SensorContainerLocalImpl.class);
		
		changeListener = Mockito.spy(new DataChangeListenerLocalImpl());
		
		changeListener.setSensorContainer(sensorContainer);
		
		TemperatureSensorVO tempSensor = new TemperatureSensorVO(1l, "Test");
		tempSensor.setData(new TemperatureDataVO(23.4));
		
		sensors = Arrays.asList(tempSensor);
		
		handlers = Arrays.asList(
			new DataChangeHandler() {
				
				@Override
				public void onChange(SensorDataVO newData) {
					handlerTest.add(1);
					System.out.println("Egy");
				}
			},
			new DataChangeHandler() {
				
				@Override
				public void onChange(SensorDataVO newData) {
					handlerTest.add(2);
					System.out.println("Egy");
				}
			},
			new DataChangeHandler() {
				
				@Override
				public void onChange(SensorDataVO newData) {
					handlerTest.add(3);
					System.out.println("Egy");
				}
			}
		);
		
		Mockito.when(sensorContainer.getSensors())
			.thenReturn(sensors);
	}

	@Test
	public void testCheckForChange() throws Exception {
		changeListener.subscribeFor(1l, handlers);
		
		changeListener.checkForChange();
		
		Assert.assertEquals(0, handlerTest.size());
		
		sensors.get(0).setData(new TemperatureDataVO(33.4));
		
		changeListener.checkForChange();
		
		Assert.assertEquals("Handlers didn't get executed!", 3, handlerTest.size());
		
		changeListener.unsubscribeAllFrom(1l);
	}

	@Test
	public void testSubscribeFor() {
		changeListener.subscribeFor(1l, handlers);
		
		List<DataChangeHandler> hs = changeListener.getHandlersFor(1l);
		
		for (int i = 0; i < hs.size(); ++i) {
			Assert.assertEquals("Not matching handler list!", handlers.get(i), hs.get(i));
		}
	}

	@Test
	public void testUnsubscribeFrom() {
		changeListener.unsubscribeFrom(1l, Arrays.asList(
				handlers.get(1), handlers.get(2)
		));
		
		List<DataChangeHandler> hs = changeListener.getHandlersFor(1l);
		
		for (int i = 0; i < hs.size(); ++i) {
			Assert.assertEquals("Not matching handler list!", handlers.get(i), hs.get(i));
		}
	}

	@Test
	public void testUnsubscribeAllFrom() {
		changeListener.unsubscribeAllFrom(1l);
		
		Assert.assertEquals("Not empty handler list!", null, changeListener.getHandlersFor(1l));
	}

}
