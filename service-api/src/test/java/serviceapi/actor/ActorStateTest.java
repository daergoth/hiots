package serviceapi.actor;

import org.junit.Assert;
import org.junit.Test;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

public class ActorStateTest {

	@Test
	public void testLampCompareTo() {
		LampActorStateVO left = new LampActorStateVO();
		left.setStatus(false);
		
		LampActorStateVO right = new LampActorStateVO();
		right.setStatus(false);
		
		ThermostatActorStateVO wrontType = new ThermostatActorStateVO();
		wrontType.setTargetTemperature(23.4);
		
		try {
			Assert.assertEquals(0, left.compareTo(right));
			
			right.setStatus(true);
			Assert.assertEquals(1, left.compareTo(right));
			
			Assert.assertEquals(0, left.compareTo(wrontType));
			Assert.fail("Should have thrown InvalidActorStateTypeException!");
		} catch (InvalidActorStateTypeException e) {}
		
		
	}
	
	@Test
	public void testThermostatCompareTo() {
		ThermostatActorStateVO left = new ThermostatActorStateVO();
		left.setTargetTemperature(23.4);
		
		ThermostatActorStateVO right = new ThermostatActorStateVO();
		right.setTargetTemperature(23.4);
		
		LampActorStateVO wrontType = new LampActorStateVO();
		wrontType.setStatus(false);
		
		try {
			Assert.assertEquals(0, left.compareTo(right));
			
			right.setTargetTemperature(13.4);
			Assert.assertEquals(1, left.compareTo(right));
			
			right.setTargetTemperature(33.4);
			Assert.assertEquals(-1, left.compareTo(right));
			
			Assert.assertEquals(0, left.compareTo(wrontType));
			Assert.fail("Should have thrown InvalidActorStateTypeException!");
		} catch (InvalidActorStateTypeException e) {}
		
		
	}

}
