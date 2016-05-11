package service.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.ConditionTypeCore;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;
import net.daergoth.service.rule.ConditionConverter;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

@FixMethodOrder(MethodSorters.JVM)
public class ConditionConverterTest {

	private static ConditionVO condVo;
	private static SensorVO sensorVo;
	private static SensorDataVO sensorDataVo;

	private static ConditionDTO condDto;
	private static SensorDTO sensorDto;
	private static SensorDataDTO sensorDataDto;

	@BeforeClass
	public static void setUpBeforeClass() {
		sensorVo = new TemperatureSensorVO(5l, "TempSensor");

		sensorDataVo = new TemperatureDataVO(23.4);

		condVo = new ConditionVO();
		condVo.setId(1l);
		condVo.setType(ConditionTypeService.EQ);
		condVo.setSensor(sensorVo);
		condVo.setValue(sensorDataVo);

		sensorDto = new SensorDTO();
		sensorDto.setId(5l);
		sensorDto.setName("TempSensor");
		sensorDto.setType("Temperature");

		sensorDataDto = new SensorDataDTO();
		sensorDataDto.setType(SensorDataType.TEMPERATURE);
		sensorDataDto.setValue(23.4);

		condDto = new ConditionDTO();
		condDto.setId(1l);
		condDto.setConditionType(ConditionTypeCore.EQ);
		condDto.setSensor(sensorDto);
		condDto.setValue(sensorDataDto);
	}

	@Test
	public void testToVO() throws SensorConvertException {
		ConditionVO newVo = ConditionConverter.toVO(condDto);

		Assert.assertEquals("Not matching IDs!", condVo.getId(), newVo.getId());

		Assert.assertEquals("Not matching ConditionType!", condVo.getType(), newVo.getType());

		Assert.assertEquals("Wrong Sensor in Condition!", condVo.getSensor().getId(), newVo.getSensor().getId());

		Assert.assertEquals("Not matching Value type!", condVo.getValue().getType(), newVo.getValue().getType());

		Assert.assertEquals("Not matching Value data", condVo.getValue().getData(), newVo.getValue().getData());
	}

	@Test
	public void testToVOs() throws SensorConvertException {
		List<ConditionDTO> dtoList = new ArrayList<>();
		dtoList.add(condDto);

		List<ConditionVO> voList = new ArrayList<>();
		voList.add(condVo);

		List<ConditionVO> actual = ConditionConverter.toVOs(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", voList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching ConditionType!", voList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Wrong Sensor in Condition!", voList.get(i).getSensor().getId(),
					actual.get(i).getSensor().getId());

			Assert.assertEquals("Not matching Value type!", voList.get(i).getValue().getType(),
					actual.get(i).getValue().getType());

			Assert.assertEquals("Not matching Value data", voList.get(i).getValue().getData(),
					actual.get(i).getValue().getData());
		}

	}

	@Test
	public void testToDTO() {
		ConditionDTO newDto = ConditionConverter.toDTO(condVo);

		Assert.assertEquals("Not matching IDs!", condDto.getId(), newDto.getId());

		Assert.assertEquals("Not matching ConditionType!", condDto.getConditionType(), newDto.getConditionType());

		Assert.assertEquals("Wrong Sensor in Condition!", condDto.getSensor().getId(), newDto.getSensor().getId());

		Assert.assertEquals("Not matching Value type!", condDto.getValue().getType(), newDto.getValue().getType());

		Assert.assertEquals("Not matching Value data", condDto.getValue().getValue(), newDto.getValue().getValue());
	}

	@Test
	public void testToDTOs() {
		List<ConditionVO> voList = new ArrayList<>();
		voList.add(condVo);

		List<ConditionDTO> dtoList = new ArrayList<>();
		dtoList.add(condDto);

		List<ConditionDTO> actual = ConditionConverter.toDTOs(voList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching ConditionType!", dtoList.get(i).getConditionType(),
					actual.get(i).getConditionType());

			Assert.assertEquals("Wrong Sensor in Condition!", dtoList.get(i).getSensor().getId(),
					actual.get(i).getSensor().getId());

			Assert.assertEquals("Not matching Value type!", dtoList.get(i).getValue().getType(),
					actual.get(i).getValue().getType());

			Assert.assertEquals("Not matching Value data", dtoList.get(i).getValue().getValue(),
					actual.get(i).getValue().getValue());
		}
	}

}
