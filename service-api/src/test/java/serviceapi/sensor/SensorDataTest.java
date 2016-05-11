package serviceapi.sensor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class SensorDataTest {
	
	@Test
	public void testLightCompareTo() {
		LightDataVO left = new LightDataVO(10);
		
		LightDataVO right = new LightDataVO(10);
		
		TemperatureDataVO wrontType = new TemperatureDataVO(23.4);
		
		try {
			assertEquals(0, left.compareTo(right));
			
			right.setLightness(1);
			assertEquals(1, left.compareTo(right));
			
			right.setLightness(100);
			assertEquals(-1, left.compareTo(right));
			
			assertEquals(0, left.compareTo(wrontType));
			fail("Should have thrown InvalidActorStateTypeException!");
		} catch (InvalidSensorDataTypeException e) {}
	}
	
	@Test
	public void testTemperatureCompareTo() {
		TemperatureDataVO left = new TemperatureDataVO(23.4);
		
		TemperatureDataVO right = new TemperatureDataVO(23.4);
		
		LightDataVO wrontType = new LightDataVO(10);
		
		try {
			assertEquals(0, left.compareTo(right));
			
			right.setTemperature(13.4);
			assertEquals(1, left.compareTo(right));
			
			right.setTemperature(33.4);
			assertEquals(-1, left.compareTo(right));
			
			assertEquals(0, left.compareTo(wrontType));
			fail("Should have thrown InvalidActorStateTypeException!");
		} catch (InvalidSensorDataTypeException e) {}
	}

}
