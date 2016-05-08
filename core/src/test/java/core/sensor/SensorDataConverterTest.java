package core.sensor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.sensor.SensorData;
import net.daergoth.core.sensor.SensorDataConverter;
import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;

public class SensorDataConverterTest {

	static SensorDataDTO dataDto;

	static SensorData dataEntity;

	@BeforeClass
	public static void setup() {
		dataDto = new SensorDataDTO();
		dataDto.setType(SensorDataType.TEMPERATURE);
		dataDto.setValue(23.4);

		dataEntity = new SensorData();
		dataEntity.setSensorDataType(SensorDataType.TEMPERATURE);
		dataEntity.setSensorDataValue(23.4);
	}

	@Test
	public void testToDTO() {
		SensorDataDTO newDto = SensorDataConverter.toDTO(dataEntity);

		Assert.assertEquals("Not matching SensorDataType!", dataDto.getType(), newDto.getType());

		Assert.assertEquals("Not matching value!", dataDto.getValue(), newDto.getValue());
	}

	@Test
	public void testToDTOs() {
		List<SensorData> entityList = new ArrayList<>();
		entityList.add(dataEntity);

		List<SensorDataDTO> dtoList = new ArrayList<>();
		dtoList.add(dataDto);

		List<SensorDataDTO> actual = SensorDataConverter.toDTOs(entityList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching SensorDataType!", dtoList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Not matching value!", dtoList.get(i).getValue(), actual.get(i).getValue());
		}

	}

	@Test
	public void testToEntity() {
		SensorData newEntity = SensorDataConverter.toEntity(dataDto);

		Assert.assertEquals("Not matching SensorDataType!", dataEntity.getSensorDataType(),
				newEntity.getSensorDataType());

		Assert.assertEquals("Not matching value!", dataEntity.getSensorDataValue(), newEntity.getSensorDataValue());
	}

	@Test
	public void testToEntities() {
		List<SensorDataDTO> dtoList = new ArrayList<>();
		dtoList.add(dataDto);

		List<SensorData> entityList = new ArrayList<>();
		entityList.add(dataEntity);

		List<SensorData> actual = SensorDataConverter.toEntities(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching SensorDataType!", entityList.get(i).getSensorDataType(),
					actual.get(i).getSensorDataType());

			Assert.assertEquals("Not matching value!", entityList.get(i).getSensorDataValue(),
					actual.get(i).getSensorDataValue());
		}
	}

}
