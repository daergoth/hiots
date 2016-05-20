package service.monitor;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.coreapi.monitor.OverviewLayoutDTO;
import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.service.monitor.OverviewLayoutConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementType;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementVO;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;

public class OverviewLayoutConverterTest {
	
	private static SensorDTO sensorDto;
	private static OverviewLayoutElementDTO elementDto;
	private static OverviewLayoutDTO layoutDto;
	
	private static SensorVO sensorVo;
	private static OverviewLayoutElementVO elementVo;
	private static OverviewLayoutVO layoutVo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sensorDto = new SensorDTO();
		sensorDto.setId(4l);
		sensorDto.setType("Temperature");
		
		elementDto = new OverviewLayoutElementDTO();
		elementDto.setId(3l);
		elementDto.setType("Sensor");
		elementDto.setSensor(sensorDto);
		elementDto.setActor(null);
		elementDto.setColumn(0);
		elementDto.setRow(0);
		
		layoutDto = new OverviewLayoutDTO();
		layoutDto.setId(1l);
		layoutDto.setName("TestLayout");
		layoutDto.setElements(Arrays.asList(elementDto));
		
		sensorVo = new TemperatureSensorVO(4l, "TestSensor");
		
		elementVo = new OverviewLayoutElementVO();
		elementVo.setId(3l);
		elementVo.setType(OverviewLayoutElementType.Sensor);
		elementVo.setSensor(sensorVo);
		elementVo.setActor(null);
		elementVo.setColumn(0);
		elementVo.setRow(0);
		
		layoutVo = new OverviewLayoutVO();
		layoutVo.setId(1l);
		layoutVo.setName("TestLayout");
		layoutVo.setElements(Arrays.asList(elementVo));
	}
	
	public void assertVO(OverviewLayoutVO expected, OverviewLayoutVO actual) {
		Assert.assertEquals("Not matching IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matching names!", expected.getName(), actual.getName());
		
		Assert.assertEquals("Not matching elements!", expected.getElements().get(0).getId(), actual.getElements().get(0).getId());
	}

	@Test
	public void testToVO() throws ActorConvertException, SensorConvertException {
		OverviewLayoutVO newVo = OverviewLayoutConverter.toVO(layoutDto);
		
		assertVO(layoutVo, newVo);
	}

	@Test
	public void testToVOs() throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutDTO> dtoList = Arrays.asList(layoutDto);  
		
		List<OverviewLayoutVO> volist = Arrays.asList(layoutVo);
		
		List<OverviewLayoutVO> actual = OverviewLayoutConverter.toVOs(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			assertVO(volist.get(i), actual.get(i));
		}
	}
	
	public void assertDTO(OverviewLayoutDTO expected, OverviewLayoutDTO actual) {
		Assert.assertEquals("Not matching IDs!", expected.getId(), actual.getId());
		
		Assert.assertEquals("Not matching names!", expected.getName(), actual.getName());
		
		Assert.assertEquals("Not matching elements!", expected.getElements().get(0).getId(), actual.getElements().get(0).getId());
	}

	@Test
	public void testToDTO() {
		OverviewLayoutDTO newDto = OverviewLayoutConverter.toDTO(layoutVo);
		
		assertDTO(layoutDto, newDto);
	}

	@Test
	public void testToDTOs() {
		List<OverviewLayoutVO> volist = Arrays.asList(layoutVo);
		
		List<OverviewLayoutDTO> dtoList = Arrays.asList(layoutDto);  
		
		List<OverviewLayoutDTO> actual = OverviewLayoutConverter.toDTOs(volist);
		
		for (int i = 0; i < actual.size(); ++i) {
			assertDTO(dtoList.get(i), actual.get(i));
		}
	}

}
