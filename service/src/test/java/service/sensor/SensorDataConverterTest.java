package service.sensor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;
import net.daergoth.service.sensor.SensorDataConverter;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

@FixMethodOrder(MethodSorters.JVM)
public class SensorDataConverterTest {

	static SensorDataVO lightVo;
	static SensorDataVO tempVo;

	static SensorDataVO fakeVo;

	static SensorDataDTO lightDto;
	static SensorDataDTO tempDto;

	static SensorDataDTO fakeDto;

	@BeforeClass
	public static void setup() {
		lightVo = new LightDataVO(10);

		tempVo = new TemperatureDataVO(23.4);

		fakeVo = new TemperatureDataVO(10);
		fakeVo.setType(null);

		lightDto = new SensorDataDTO();
		lightDto.setType(SensorDataType.LIGHT);
		lightDto.setValue(10.0);

		tempDto = new SensorDataDTO();
		tempDto.setType(SensorDataType.TEMPERATURE);
		tempDto.setValue(23.4);

		fakeDto = new SensorDataDTO();
		fakeDto.setType(null);
	}

	@Test
	public void testToVO() {
		SensorDataVO newVo = SensorDataConverter.toVO(lightDto);

		Assert.assertEquals("Not matching SensorDataType!", lightVo.getType(), newVo.getType());

		Assert.assertEquals("Not matching value!", lightVo.getData(), newVo.getData());

		newVo = SensorDataConverter.toVO(tempDto);

		Assert.assertEquals("Not matching SensorDataType!", tempVo.getType(), newVo.getType());

		Assert.assertEquals("Not matching value!", tempVo.getData(), newVo.getData());

		/*
		 * TODO: newVo = SensorDataConverter.toVO(fakeDto);
		 * 
		 * Assert.assertEquals("Created SensorData with wrong type!", null,
		 * newVo);
		 */
	}

	@Test
	public void testToVOs() {
		List<SensorDataDTO> dtoList = new ArrayList<>();
		dtoList.add(lightDto);
		dtoList.add(tempDto);

		List<SensorDataVO> voList = new ArrayList<SensorDataVO>();
		voList.add(lightVo);
		voList.add(tempVo);

		List<SensorDataVO> actual = SensorDataConverter.toVOs(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching SensorDataType!", voList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Not matching value!", voList.get(i).getData(), actual.get(i).getData());
		}

	}

	@Test
	public void testToDTO() {
		SensorDataDTO newDto = SensorDataConverter.toDTO(lightVo);

		Assert.assertEquals("Not matching SensorDataType!", lightDto.getType(), newDto.getType());

		Assert.assertEquals("Not matching value!", lightDto.getValue(), newDto.getValue());

		newDto = SensorDataConverter.toDTO(tempVo);

		Assert.assertEquals("Not matching SensorDataType!", tempDto.getType(), newDto.getType());

		Assert.assertEquals("Not matching value!", tempDto.getValue(), newDto.getValue());

		/*
		 * TODO: newDto = SensorDataConverter.toDTO(fakeVo);
		 * 
		 * Assert.assertEquals("Created SensorData with wrong type!", null,
		 * newDto);
		 */
	}

	@Test
	public void testToDTOs() {
		List<SensorDataVO> voList = new ArrayList<SensorDataVO>();
		voList.add(lightVo);
		voList.add(tempVo);

		List<SensorDataDTO> dtoList = new ArrayList<>();
		dtoList.add(lightDto);
		dtoList.add(tempDto);

		List<SensorDataDTO> actual = SensorDataConverter.toDTOs(voList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching SensorDataType!", dtoList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Not matching value!", dtoList.get(i).getValue(), actual.get(i).getValue());
		}
	}

}
