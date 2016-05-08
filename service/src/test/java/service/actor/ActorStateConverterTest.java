package service.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;
import net.daergoth.service.actor.ActorStateConverter;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

@FixMethodOrder(MethodSorters.JVM)
public class ActorStateConverterTest {
	
	static ActorStateVO lampVo;
	static ActorStateVO thermoVo;
	
	static ActorStateDTO lampDto;
	static ActorStateDTO thermoDto;
	
	@BeforeClass
	public static void setup() {
		lampVo = new LampActorStateVO();
		lampVo.setData(1.0);
		
		thermoVo = new ThermostatActorStateVO();
		thermoVo.setData(25.0);
		
		lampDto = new ActorStateDTO();
		lampDto.setType(ActorStateType.LAMP);
		lampDto.setValue(1.0);
		
		thermoDto = new ActorStateDTO();
		thermoDto.setType(ActorStateType.THERMOSTAT);
		thermoDto.setValue(25.0);
	}

	@Test
	public void testToDTO() {
		ActorStateDTO newDto = ActorStateConverter.toDTO(lampVo);
		
		Assert.assertEquals("Not matching ActorStateType!", lampDto.getType(), newDto.getType());
		
		Assert.assertEquals("Not matching value!", lampDto.getValue(), newDto.getValue());
		
		newDto = ActorStateConverter.toDTO(thermoVo);
		
		Assert.assertEquals("Not matching ActorStateType!", thermoDto.getType(), newDto.getType());
		
		Assert.assertEquals("Not matching value!", thermoDto.getValue(), newDto.getValue());
	}

	@Test
	public void testToDTOs() {
		List<ActorStateVO> voList = new ArrayList<>();
		voList.add(lampVo);
		voList.add(thermoVo);
		
		List<ActorStateDTO> dtoList = new ArrayList<>();
		dtoList.add(lampDto);
		dtoList.add(thermoDto);
		
		List<ActorStateDTO> actual = ActorStateConverter.toDTOs(voList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching ActorStateType!", dtoList.get(i).getType(), actual.get(i).getType());
			
			Assert.assertEquals("Not matching value!", dtoList.get(i).getValue(), actual.get(i).getValue());
		}
	}

	@Test
	public void testToVO() {
		ActorStateVO newVo = ActorStateConverter.toVO(lampDto);
		
		Assert.assertEquals("Not matching ActorStateType!", lampVo.getType(), newVo.getType());
		
		Assert.assertEquals("Not matching value!", lampVo.getData(), newVo.getData());
		
		newVo = ActorStateConverter.toVO(thermoDto);
		
		Assert.assertEquals("Not matching ActorStateType!", thermoVo.getType(), newVo.getType());
		
		Assert.assertEquals("Not matching value!", thermoVo.getData(), newVo.getData());
	}

	@Test
	public void testToVOs() {
		List<ActorStateDTO> dtoList = new ArrayList<>();
		dtoList.add(lampDto);
		dtoList.add(thermoDto);
		
		List<ActorStateVO> voList = new ArrayList<>();
		voList.add(lampVo);
		voList.add(thermoVo);
		
		List<ActorStateVO> actual = ActorStateConverter.toVOs(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching ActorStateType!", voList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Not matching value!", voList.get(i).getData(), actual.get(i).getData());
		}
	}

}
