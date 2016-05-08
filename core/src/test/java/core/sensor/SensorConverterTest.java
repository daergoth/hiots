package core.sensor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.core.sensor.DummySensorInformation;
import net.daergoth.core.sensor.Sensor;
import net.daergoth.core.sensor.SensorConverter;
import net.daergoth.coreapi.sensor.DummySensorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

@FixMethodOrder(MethodSorters.JVM)
public class SensorConverterTest {

	static SensorDTO sensorDto;
	static DummySensorDTO dummySensorDto;

	static Sensor sensorEntity;
	static Sensor dummySensorEntity;
	static DummySensorInformation dummyInfo;

	@BeforeClass
	public static void setup() {

		sensorDto = new SensorDTO();
		sensorDto.setId(1l);
		sensorDto.setName("Sensor");
		sensorDto.setType("Temperature");

		dummySensorDto = new DummySensorDTO();
		dummySensorDto.setId(2l);
		dummySensorDto.setName("DummySensor");
		dummySensorDto.setType("Light");
		dummySensorDto.setMin(20.0);
		dummySensorDto.setMax(30.0);
		dummySensorDto.setInterval(1000);

		sensorEntity = new Sensor();
		sensorEntity.setId(1l);
		sensorEntity.setName("Sensor");
		sensorEntity.setType("Temperature");
		sensorEntity.setDummyInfo(null);

		dummyInfo = new DummySensorInformation();
		dummyInfo.setSensorId(2l);
		dummyInfo.setMinData(20.0);
		dummyInfo.setMaxData(30.0);
		dummyInfo.setRefreshInterval(1000);

		dummySensorEntity = new Sensor();
		dummySensorEntity.setId(2l);
		dummySensorEntity.setName("DummySensor");
		dummySensorEntity.setType("Light");
		dummySensorEntity.setDummyInfo(dummyInfo);

	}

	@Test
	public void testToDTO() {
		SensorDTO newDto = SensorConverter.toDTO(sensorEntity);

		Assert.assertEquals("Not matching IDs!", sensorDto.getId(), newDto.getId());

		Assert.assertEquals("Not matching names!", sensorDto.getName(), newDto.getName());

		Assert.assertEquals("Not matching SensorType!", sensorDto.getType(), newDto.getType());

		newDto = SensorConverter.toDTO(dummySensorEntity);

		if (newDto instanceof DummySensorDTO) {

			DummySensorDTO dummyDto = (DummySensorDTO) newDto;

			Assert.assertEquals("Not matching IDs!", dummySensorDto.getId(), dummyDto.getId());

			Assert.assertEquals("Not matching names!", dummySensorDto.getName(), dummyDto.getName());

			Assert.assertEquals("Not matching SensorType!", dummySensorDto.getType(), dummyDto.getType());

			Assert.assertEquals("Not matching Dummy lower limit!", dummySensorDto.getMin(), dummyDto.getMin(), 0.0);

			Assert.assertEquals("Not matching Dummy upper limit!", dummySensorDto.getMax(), dummyDto.getMax(), 0.0);

			Assert.assertEquals("Not matching Dummy refresh interval!", dummySensorDto.getInterval(),
					dummyDto.getInterval());

		} else {
			fail("Return not DummySensorDTO for Sensor /w DummySensorInformation!");
		}

	}

	@Test
	public void testToDTOs() {
		List<Sensor> entityList = new ArrayList<>();
		entityList.add(sensorEntity);
		entityList.add(dummySensorEntity);
		
		List<SensorDTO> dtoList = new ArrayList<>();
		dtoList.add(sensorDto);
		dtoList.add(dummySensorDto);
		
		List<SensorDTO> actual = SensorConverter.toDTOs(entityList);
		
		for (int i = 0; i < actual.size(); ++i) {
			if (dtoList.get(i) instanceof DummySensorDTO) {
				DummySensorDTO dummyDto = (DummySensorDTO) dtoList.get(i);

				Assert.assertEquals("Not matching IDs!", dummySensorDto.getId(), dummyDto.getId());

				Assert.assertEquals("Not matching names!", dummySensorDto.getName(), dummyDto.getName());

				Assert.assertEquals("Not matching SensorType!", dummySensorDto.getType(), dummyDto.getType());

				Assert.assertEquals("Not matching Dummy lower limit!", dummySensorDto.getMin(), dummyDto.getMin(), 0.0);

				Assert.assertEquals("Not matching Dummy upper limit!", dummySensorDto.getMax(), dummyDto.getMax(), 0.0);

				Assert.assertEquals("Not matching Dummy refresh interval!", dummySensorDto.getInterval(),
						dummyDto.getInterval());
				
			} else {
				if (actual.get(i) instanceof DummySensorDTO) {
					fail("Expected SensorDTO isn't DummySensorDTO!");
				}
				
				Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

				Assert.assertEquals("Not matching names!", dtoList.get(i).getName(), actual.get(i).getName());

				Assert.assertEquals("Not matching SensorType!", dtoList.get(i).getType(), actual.get(i).getType());

			}
		}
	}

	@Test
	public void testToEntity() {
		Sensor newEntity = SensorConverter.toEntity(sensorDto);

		Assert.assertEquals("Not matching IDs!", sensorEntity.getId(), newEntity.getId());

		Assert.assertEquals("Not matching names!", sensorEntity.getName(), newEntity.getName());

		Assert.assertEquals("Not matching SensorType!", sensorEntity.getType(), newEntity.getType());

		newEntity = SensorConverter.toEntity(dummySensorDto);

		if (newEntity.getDummyInfo() != null) {

			Assert.assertEquals("Not matching IDs!", dummySensorEntity.getId(), newEntity.getId());

			Assert.assertEquals("Not matching names!", dummySensorEntity.getName(), newEntity.getName());

			Assert.assertEquals("Not matching SensorType!", dummySensorEntity.getType(), newEntity.getType());
			
			Assert.assertEquals("Not matching Dummy SensorId!", dummySensorEntity.getDummyInfo().getSensorId(), 
					newEntity.getDummyInfo().getSensorId());

			Assert.assertEquals("Not matching Dummy lower limit!", dummySensorEntity.getDummyInfo().getMinData(),
					newEntity.getDummyInfo().getMinData(), 0.0);

			Assert.assertEquals("Not matching Dummy upper limit!", dummySensorEntity.getDummyInfo().getMaxData(),
					newEntity.getDummyInfo().getMaxData(), 0.0);

			Assert.assertEquals("Not matching Dummy refresh interval!",
					dummySensorEntity.getDummyInfo().getRefreshInterval(),
					newEntity.getDummyInfo().getRefreshInterval());

		} else {
			fail("Return not Sensor /w DummySensorInformation for DummySensorDTO!");
		}
	}

	@Test
	public void testToEntities() {
		List<SensorDTO> dtoList = new ArrayList<>();
		dtoList.add(sensorDto);
		dtoList.add(dummySensorDto);
		
		List<Sensor> entityList = new ArrayList<>();
		entityList.add(sensorEntity);
		entityList.add(dummySensorEntity);
		
		List<Sensor> actual = SensorConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			if (entityList.get(i).getDummyInfo() != null) {
				
				Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());

				Assert.assertEquals("Not matching names!", entityList.get(i).getName(), actual.get(i).getName());

				Assert.assertEquals("Not matching SensorType!", entityList.get(i).getType(), actual.get(i).getType());
				
				Assert.assertEquals("Not matching Dummy SensorId!", entityList.get(i).getDummyInfo().getSensorId(), 
						actual.get(i).getDummyInfo().getSensorId());

				Assert.assertEquals("Not matching Dummy lower limit!", entityList.get(i).getDummyInfo().getMinData(),
						actual.get(i).getDummyInfo().getMinData(), 0.0);

				Assert.assertEquals("Not matching Dummy upper limit!", entityList.get(i).getDummyInfo().getMaxData(),
						actual.get(i).getDummyInfo().getMaxData(), 0.0);

				Assert.assertEquals("Not matching Dummy refresh interval!",
						entityList.get(i).getDummyInfo().getRefreshInterval(),
						actual.get(i).getDummyInfo().getRefreshInterval());
				
			} else {
				if (actual.get(i).getDummyInfo() != null) {
					fail("Expected SensorDTO isn't DummySensorDTO!");
				}
				
				Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());

				Assert.assertEquals("Not matching names!", entityList.get(i).getName(), actual.get(i).getName());

				Assert.assertEquals("Not matching SensorType!", entityList.get(i).getType(), actual.get(i).getType());

			}
		}
	}

}
