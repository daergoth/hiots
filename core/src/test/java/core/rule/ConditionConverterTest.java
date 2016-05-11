package core.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.rule.Condition;
import net.daergoth.core.rule.ConditionConverter;
import net.daergoth.core.sensor.Sensor;
import net.daergoth.core.sensor.SensorData;
import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.ConditionTypeCore;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;

public class ConditionConverterTest {

	private static SensorDTO sensorDto;
	private static SensorDataDTO valueDto;
	private static ConditionDTO condDto;

	private static Sensor sensorEntity;
	private static SensorData valueEntity;
	private static Condition condEntity;

	@BeforeClass
	public static void setUpBeforeClass() {
		sensorDto = new SensorDTO();
		sensorDto.setId(1l);
		sensorDto.setName("TestSensor");
		sensorDto.setType("Temperature");

		valueDto = new SensorDataDTO();
		valueDto.setType(SensorDataType.TEMPERATURE);
		valueDto.setValue(23.4);

		condDto = new ConditionDTO();
		condDto.setId(1l);
		condDto.setConditionType(ConditionTypeCore.EQ);
		condDto.setSensor(sensorDto);
		condDto.setValue(valueDto);

		sensorEntity = new Sensor();
		sensorEntity.setId(1l);
		sensorEntity.setName("TestSensor");
		sensorEntity.setType("Temperature");
		sensorEntity.setDummyInfo(null);

		valueEntity = new SensorData();
		valueEntity.setSensorDataType(SensorDataType.TEMPERATURE);
		valueEntity.setSensorDataValue(23.4);

		condEntity = new Condition();
		condEntity.setId(1l);
		condEntity.setType(ConditionTypeCore.EQ);
		condEntity.setSensor(sensorEntity);
		condEntity.setValue(valueEntity);

	}

	@Test
	public void testToDTO() {
		ConditionDTO newDto = ConditionConverter.toDTO(condEntity);

		Assert.assertEquals("Not matching IDs!", condDto.getId(), newDto.getId());

		Assert.assertEquals("Not matching ConditionType!", condDto.getConditionType(), newDto.getConditionType());

		Assert.assertEquals("Wrong Sensor in Condition!", condDto.getSensor().getId(), newDto.getSensor().getId());

		Assert.assertEquals("Wrong SensorData in Condition!", condDto.getValue().getType(), newDto.getValue().getType());
	}

	@Test
	public void testToDTOs() {
		List<Condition> entityList = new ArrayList<>();
		entityList.add(condEntity);
		
		List<ConditionDTO> dtoList = new ArrayList<>();
		dtoList.add(condDto);
		
		List<ConditionDTO> actual = ConditionConverter.toDTOs(entityList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching ConditionType!", dtoList.get(i).getConditionType(), actual.get(i).getConditionType());

			Assert.assertEquals("Wrong Sensor in Condition!", dtoList.get(i).getSensor().getId(), actual.get(i).getSensor().getId());

			Assert.assertEquals("Wrong SensorData in Condition!", dtoList.get(i).getValue().getType(), actual.get(i).getValue().getType());
		}
	}

	@Test
	public void testToEntity() {
		Condition newEntity = ConditionConverter.toEntity(condDto);
		
		Assert.assertEquals("Not matching IDs!", condEntity.getId(), newEntity.getId());

		Assert.assertEquals("Not matching ConditionType!", condEntity.getType(), newEntity.getType());

		Assert.assertEquals("Wrong Sensor in Condition!", condEntity.getSensor().getId(), newEntity.getSensor().getId());

		Assert.assertEquals("Wrong SensorData in Condition!", condEntity.getValue().getSensorDataType(), newEntity.getValue().getSensorDataType());
	}

	@Test
	public void testToEntities() {
		List<ConditionDTO> dtoList = new ArrayList<>();
		dtoList.add(condDto);
		
		List<Condition> entityList = new ArrayList<>();
		entityList.add(condEntity);
		
		List<Condition> actual = ConditionConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching ConditionType!", entityList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Wrong Sensor in Condition!", entityList.get(i).getSensor().getId(), actual.get(i).getSensor().getId());

			Assert.assertEquals("Wrong SensorData in Condition!", entityList.get(i).getValue().getSensorDataType(), actual.get(i).getValue().getSensorDataType());
		}
	}

}
