package core.monitor;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.monitor.OverviewLayout;
import net.daergoth.core.monitor.OverviewLayoutConverter;
import net.daergoth.core.monitor.OverviewLayoutElement;
import net.daergoth.core.sensor.Sensor;
import net.daergoth.coreapi.monitor.OverviewLayoutDTO;
import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

public class OverviewLayoutConverterTest {
	private static SensorDTO sensorDto;
	private static OverviewLayoutElementDTO elementDto;
	private static OverviewLayoutDTO layoutDto;
	
	private static Sensor sensorEntity;
	private static OverviewLayoutElement elementEntity;
	private static OverviewLayout layoutEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sensorDto = new SensorDTO();
		sensorDto.setId(5l);
		
		elementDto = new OverviewLayoutElementDTO();
		elementDto.setId(1l);
		elementDto.setType("Sensor");
		elementDto.setSensor(sensorDto);
		elementDto.setActor(null);
		elementDto.setColumn(0);
		elementDto.setRow(0);
		
		layoutDto = new OverviewLayoutDTO();
		layoutDto.setId(1l);
		layoutDto.setName("TestLayout");
		layoutDto.setElements(Arrays.asList(elementDto));
		
		sensorEntity = new Sensor();
		sensorEntity.setId(5l);
		
		elementEntity = new OverviewLayoutElement();
		elementEntity.setId(1l);
		elementEntity.setType("Sensor");
		elementEntity.setSensor(sensorEntity);
		elementEntity.setActor(null);
		elementEntity.setColumn(0);
		elementEntity.setRow(0);
		
		layoutEntity = new OverviewLayout();
		layoutEntity.setId(1l);
		layoutEntity.setName("TestLayout");
		layoutEntity.setElements(Arrays.asList(elementEntity));
		
	}
	
	public void assertDto(OverviewLayoutDTO expected, OverviewLayoutDTO actual) {
		Assert.assertEquals("Not matchings IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matchings names!", expected.getName(), actual.getName());
		
		Assert.assertEquals("Not matchings elements!", expected.getElements().get(0).getId(), actual.getElements().get(0).getId());
	}

	@Test
	public void testToDTO() {
		OverviewLayoutDTO newDto = OverviewLayoutConverter.toDTO(layoutEntity);
		
		assertDto(layoutDto, newDto);
	}

	@Test
	public void testToDTOs() {
		List<OverviewLayout> entityList = Arrays.asList(layoutEntity);
		
		List<OverviewLayoutDTO> dtoList = Arrays.asList(layoutDto);
		
		List<OverviewLayoutDTO> actual = OverviewLayoutConverter.toDTOs(entityList);
		
		for (int i = 0; i < actual.size(); i++) {
			assertDto(dtoList.get(i), actual.get(i));
		}
	}
	
	public void assertEntity(OverviewLayout expected, OverviewLayout actual) {
		Assert.assertEquals("Not matchings IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matchings names!", expected.getName(), actual.getName());
		
		Assert.assertEquals("Not matchings elements!", expected.getElements().get(0).getId(), actual.getElements().get(0).getId());
	}

	@Test
	public void testToEntity() {
		OverviewLayout newEntity = OverviewLayoutConverter.toEntity(layoutDto);
		
		assertEntity(layoutEntity, newEntity);
	}

	@Test
	public void testToEntities() {
		List<OverviewLayoutDTO> dtoList = Arrays.asList(layoutDto);
		
		List<OverviewLayout> entityList = Arrays.asList(layoutEntity);
		
		List<OverviewLayout> actual = OverviewLayoutConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); i++) {
			assertEntity(entityList.get(i), actual.get(i));
		}
	}

}
