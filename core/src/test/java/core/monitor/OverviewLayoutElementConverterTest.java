package core.monitor;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.actor.Actor;
import net.daergoth.core.monitor.OverviewLayoutElement;
import net.daergoth.core.monitor.OverviewLayoutElementConverter;
import net.daergoth.core.sensor.Sensor;
import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

public class OverviewLayoutElementConverterTest {
	
	private static Sensor sensorEntity;
	private static OverviewLayoutElement elementSensorEntity;
	
	private static SensorDTO sensorDto;
	private static OverviewLayoutElementDTO elementSensorDto;
	
	private static Actor actorEntity;
	private static OverviewLayoutElement elementActorEntity;
	
	private static ActorDTO actorDto;
	private static OverviewLayoutElementDTO elementActorDto;

	@BeforeClass
	public static void setUpBeforeClass() {
		sensorDto = new SensorDTO();
		sensorDto.setId(5l);
		
		elementSensorDto = new OverviewLayoutElementDTO();
		elementSensorDto.setId(1l);
		elementSensorDto.setType("Sensor");
		elementSensorDto.setSensor(sensorDto);
		elementSensorDto.setActor(null);
		elementSensorDto.setColumn(0);
		elementSensorDto.setRow(0);
		
		sensorEntity = new Sensor();
		sensorEntity.setId(5l);
		
		elementSensorEntity = new OverviewLayoutElement();
		elementSensorEntity.setId(1l);
		elementSensorEntity.setType("Sensor");
		elementSensorEntity.setSensor(sensorEntity);
		elementSensorEntity.setActor(null);
		elementSensorEntity.setColumn(0);
		elementSensorEntity.setRow(0);
		
		actorDto = new ActorDTO();
		actorDto.setId(4l);
		
		elementActorDto = new OverviewLayoutElementDTO();
		elementActorDto.setId(2l);
		elementActorDto.setType("Actor");
		elementActorDto.setSensor(null);
		elementActorDto.setActor(actorDto);
		elementActorDto.setColumn(1);
		elementActorDto.setRow(0);
		
		actorEntity = new Actor();
		actorEntity.setId(4l);
		
		elementActorEntity = new OverviewLayoutElement();
		elementActorEntity.setId(2l);
		elementActorEntity.setType("Actor");
		elementActorEntity.setSensor(null);
		elementActorEntity.setActor(actorEntity);
		elementActorEntity.setColumn(1);
		elementActorEntity.setRow(0);
	}
	
	public void assertDto(OverviewLayoutElementDTO expected, OverviewLayoutElementDTO actual) {
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
		OverviewLayoutElementDTO newDto = OverviewLayoutElementConverter.toDTO(elementSensorEntity);
		
		assertDto(elementSensorDto, newDto);
		
		newDto = OverviewLayoutElementConverter.toDTO(elementActorEntity);
		
		assertDto(elementActorDto, newDto);
	}

	@Test
	public void testToDTOs() {
		List<OverviewLayoutElement> entityList = Arrays.asList(elementSensorEntity, elementActorEntity);
		
		List<OverviewLayoutElementDTO> dtoList = Arrays.asList(elementSensorDto, elementActorDto);
		
		List<OverviewLayoutElementDTO> actual = OverviewLayoutElementConverter.toDTOs(entityList);
		
		for (int i = 0; i < actual.size(); i++) {
			assertDto(dtoList.get(i), actual.get(i));
		}
	}
	
	public void assertEntity(OverviewLayoutElement expected, OverviewLayoutElement actual) {
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
	public void testToEntity() {
		OverviewLayoutElement newEntity = OverviewLayoutElementConverter.toEntity(elementSensorDto);
		
		assertEntity(elementSensorEntity, newEntity);
		
		newEntity = OverviewLayoutElementConverter.toEntity(elementActorDto);
		
		assertEntity(elementActorEntity, newEntity);
	}

	@Test
	public void testToEntities() {
		List<OverviewLayoutElementDTO> dtoList = Arrays.asList(elementSensorDto, elementActorDto);
		
		List<OverviewLayoutElement> entityList = Arrays.asList(elementSensorEntity, elementActorEntity);
		
		List<OverviewLayoutElement> actual = OverviewLayoutElementConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); i++) {
			assertEntity(entityList.get(i), actual.get(i));
		}
	}

}
