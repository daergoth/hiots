package service.monitor;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.service.monitor.OverviewLayoutElementConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementType;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;

public class OverviewLayoutElementConverterTest {
	
	private static SensorDTO sensorDto;
	private static SensorVO sensorVo;
	private static OverviewLayoutElementDTO elementSensorDto;
	private static OverviewLayoutElementVO elementSensorVo;
	
	private static ActorDTO actorDto;
	private static ActorVO actorVo;
	private static OverviewLayoutElementDTO elementActorDto;
	private static OverviewLayoutElementVO elementActorVo;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sensorDto = new SensorDTO();
		sensorDto.setId(5l);
		sensorDto.setType("Temperature");
		
		elementSensorDto = new OverviewLayoutElementDTO();
		elementSensorDto.setId(1l);
		elementSensorDto.setType("Sensor");
		elementSensorDto.setSensor(sensorDto);
		elementSensorDto.setActor(null);
		elementSensorDto.setColumn(0);
		elementSensorDto.setRow(0);
		
		sensorVo = new TemperatureSensorVO(5l, "TestTempSensor");
		
		elementSensorVo = new OverviewLayoutElementVO();
		elementSensorVo.setId(1l);
		elementSensorVo.setType(OverviewLayoutElementType.Sensor);
		elementSensorVo.setSensor(sensorVo);
		elementSensorVo.setActor(null);
		elementSensorVo.setColumn(0);
		elementSensorVo.setRow(0);
		
		actorDto = new ActorDTO();
		actorDto.setId(4l);
		actorDto.setType("Thermostat");
		
		elementActorDto = new OverviewLayoutElementDTO();
		elementActorDto.setId(2l);
		elementActorDto.setType("Actor");
		elementActorDto.setSensor(null);
		elementActorDto.setActor(actorDto);
		elementActorDto.setColumn(1);
		elementActorDto.setRow(0);
		
		actorVo = new ThermostatActorVO(4l, "TestThermoActor");
		
		elementActorVo = new OverviewLayoutElementVO();
		elementActorVo.setId(2l);
		elementActorVo.setType(OverviewLayoutElementType.Actor);
		elementActorVo.setSensor(null);
		elementActorVo.setActor(actorVo);
		elementActorVo.setColumn(1);
		elementActorVo.setRow(0);
	}
	
	public void assertVO(OverviewLayoutElementVO expected, OverviewLayoutElementVO actual) {
		Assert.assertEquals("Not matching IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matching types!", expected.getType(), actual.getType());
		
		if (expected.getType() == OverviewLayoutElementType.Sensor) {
			Assert.assertEquals("Not matching referenced Sensor!", expected.getSensor().getId().longValue(), actual.getSensor().getId().longValue());
		} else if (expected.getType() == OverviewLayoutElementType.Actor) {
			Assert.assertEquals("Not matching referenced Actor!", expected.getActor().getId().longValue(), actual.getActor().getId().longValue());
		}
		
		Assert.assertEquals("Not matching column numbers!", expected.getColumn(), actual.getColumn());
		
		Assert.assertEquals("Not matching row numbers!", expected.getRow(), actual.getRow());
	}

	@Test
	public void testToVO() throws ActorConvertException, SensorConvertException {
		OverviewLayoutElementVO newVo = OverviewLayoutElementConverter.toVO(elementSensorDto);
		
		assertVO(elementSensorVo, newVo);
		
		newVo = OverviewLayoutElementConverter.toVO(elementActorDto);
		
		assertVO(elementActorVo, newVo);
	}

	@Test
	public void testToVOs() throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutElementDTO> dtoList = Arrays.asList(elementSensorDto, elementActorDto);
		
		List<OverviewLayoutElementVO> voList = Arrays.asList(elementSensorVo ,elementActorVo);
		
		List<OverviewLayoutElementVO> actual = OverviewLayoutElementConverter.toVOs(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			assertVO(voList.get(i), actual.get(i));
		}
	}
	
	public void assertDTO(OverviewLayoutElementDTO expected, OverviewLayoutElementDTO actual) {
		Assert.assertEquals("Not matching IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matching types!", expected.getType(), actual.getType());
		
		if (expected.getType().equals("Sensor")) {
			Assert.assertEquals("Not matching referenced Sensor!", expected.getSensor().getId(), actual.getSensor().getId());
		} else if (expected.getType().equals("Actor")) {
			Assert.assertEquals("Not matching referenced Actor!", expected.getActor().getId(), actual.getActor().getId());
		}
		
		Assert.assertEquals("Not matching column numbers!", expected.getColumn(), actual.getColumn());
		
		Assert.assertEquals("Not matching row numbers!", expected.getRow(), actual.getRow());
	}

	@Test
	public void testToDTO() {
		OverviewLayoutElementDTO newDto = OverviewLayoutElementConverter.toDTO(elementSensorVo);
		
		assertDTO(elementSensorDto, newDto);
		
		newDto = OverviewLayoutElementConverter.toDTO(elementActorVo);
		
		assertDTO(elementActorDto, newDto);
	}

	@Test
	public void testToDTOs() {
		List<OverviewLayoutElementVO> voList = Arrays.asList(elementSensorVo ,elementActorVo);
		
		List<OverviewLayoutElementDTO> dtoList = Arrays.asList(elementSensorDto, elementActorDto);
		
		List<OverviewLayoutElementDTO> actual = OverviewLayoutElementConverter.toDTOs(voList);
		
		for (int i = 0; i < actual.size(); ++i) {
			assertDTO(dtoList.get(i), actual.get(i));
		}
	}

}
