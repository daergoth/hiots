package service.actor;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.DummyActorDTO;
import net.daergoth.service.actor.ActorConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;

@FixMethodOrder(MethodSorters.JVM)
public class ActorConverterTest {
	
	static ActorVO lampVo;
	static ActorVO thermoVo;
	
	static DummyActorVO lampDummyVo;
	static DummyActorVO thermoDummyVo;
	
	static ActorDTO lampDto;
	static ActorDTO thermoDto;
	
	static DummyActorDTO lampDummyDto;
	static DummyActorDTO thermoDummyDto;
	
	static ActorDTO fakeDto;
	
	@BeforeClass
	public static void setup() {
		lampVo = new LampActorVO(5l, "LampActor");
		thermoVo = new ThermostatActorVO(6l, "ThermostatActor");
		lampDummyVo = new DummyLampActorVO(7l, "DummyLampActor");
		thermoDummyVo = new DummyThermostatActorVO(8l, "DummyThemostatActor");
		
		lampDto = new ActorDTO();
		lampDto.setId(5l);
		lampDto.setName("LampActor");
		lampDto.setType("Lamp");
		
		thermoDto = new ActorDTO();
		thermoDto.setId(6l);
		thermoDto.setName("ThermostatActor");
		thermoDto.setType("Thermostat");
		
		lampDummyDto = new DummyActorDTO();
		lampDummyDto.setId(7l);
		lampDummyDto.setName("DummyLampActor");
		lampDummyDto.setType("Lamp");
		
		thermoDummyDto = new DummyActorDTO();
		thermoDummyDto.setId(8l);
		thermoDummyDto.setName("DummyThemostatActor");
		thermoDummyDto.setType("Thermostat");
		
		fakeDto = new ActorDTO();
		fakeDto.setType("Wrong Type");
		
	}

	@Test
	public void testToVO() {
		try {
			ActorVO newVo = ActorConverter.toVO(lampDto);
			
			Assert.assertEquals("Not matching IDs!", lampVo.getId(), newVo.getId());
			Assert.assertEquals("Not matching names!", lampVo.getName(), newVo.getName());
			Assert.assertEquals("Not matching ActorType!", lampVo.getType(), newVo.getType());
			
			newVo = ActorConverter.toVO(thermoDto);
			
			Assert.assertEquals("Not matching IDs!", thermoVo.getId(), newVo.getId());
			Assert.assertEquals("Not matching names!", thermoVo.getName(), newVo.getName());
			Assert.assertEquals("Not matching ActorType!", thermoVo.getType(), newVo.getType());
			
			
		} catch (ActorConvertException e) {
			fail();
		}
	}
	
	@Test
	public void testToDummyVO() {
		try {
			ActorVO newVo = ActorConverter.toVO(lampDummyDto);
			
			if (!(newVo instanceof DummyActorVO)) {
				fail("Return for DummyDTO isn't DummyVO!");
			} else {
				DummyActorVO dummyVo = (DummyActorVO) newVo;
				
				Assert.assertEquals("Not matching IDs!", lampDummyVo.getId(), dummyVo.getId());
				Assert.assertEquals("Not matching names!", lampDummyVo.getName(), dummyVo.getName());
				Assert.assertEquals("Not matching ActorType!", lampDummyVo.getType(), dummyVo.getType());
			}
			
			newVo = ActorConverter.toVO(thermoDummyDto);
			
			if (!(newVo instanceof DummyActorVO)) {
				fail("Return for DummyDTO isn't DummyVO!");
			} else {
				DummyActorVO dummyVo = (DummyActorVO) newVo;
				
				Assert.assertEquals("Not matching IDs!", thermoDummyVo.getId(), dummyVo.getId());
				Assert.assertEquals("Not matching names!", thermoDummyVo.getName(), dummyVo.getName());
				Assert.assertEquals("Not matching ActorType!", thermoDummyVo.getType(), dummyVo.getType());
			}
			
		} catch (ActorConvertException e) {
			fail();
		}
		
	}
	
	@Test(expected = ActorConvertException.class)
	public void testWrongTypeToVO() throws ActorConvertException {
		ActorVO newVo = ActorConverter.toVO(fakeDto);
	}
	

	@Test
	public void testToDTO() {
		ActorDTO newDto = ActorConverter.toDTO(lampVo);
		
		Assert.assertEquals("Not matching IDs!", lampDto.getId(), newDto.getId());
		Assert.assertEquals("Not matching name!", lampDto.getName(), newDto.getName());
		Assert.assertEquals("Not matching ActorType!", lampDto.getType(), newDto.getType());
	}
	
	@Test
	public void testToDummyDTO() {
		ActorDTO newDto = ActorConverter.toDTO(lampDummyVo);
		
		if (!(newDto instanceof DummyActorDTO)) {
			fail("Return for DummyDTO isn't DummyVO!");
		} else {
			DummyActorDTO dummyDTO = (DummyActorDTO) newDto;
			
			Assert.assertEquals("Not matching IDs!", lampDummyDto.getId(), dummyDTO.getId());
			Assert.assertEquals("Not matching names!", lampDummyDto.getName(), dummyDTO.getName());
			Assert.assertEquals("Not matching ActorType!", lampDummyDto.getType(), dummyDTO.getType());
		}
	}

}
