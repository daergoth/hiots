package service.sensor;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.sensor.DummySensorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.service.sensor.SensorConverter;
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@FixMethodOrder(MethodSorters.JVM)
public class SensorConverterTest {
	
	static SensorVO tempVO;
	static SensorVO lightVO;
	static DummySensorVO tempDummyVO;
	static DummySensorVO lightDummyVO;
	
	static SensorDTO tempDTO;
	static SensorDTO lightDTO;
	static DummySensorDTO tempDummyDTO;
	static DummySensorDTO lightDummyDTO;
	
	static SensorDTO fakeDTO;
	
	@BeforeClass
	public static void setup() {
		
		tempVO = new TemperatureSensorVO(5l, "TemperatureSensor");
		
		lightVO = new LightSensorVO(6l, "LightSensor");
		
		tempDummyVO = new DummyTemperatureSensorVO(
				7l,
				"DummyTemperatureSensor", 
				20, 
				25, 
				1000);
		
		lightDummyVO = new DummyLightSensorVO(
				8l,
				"DummyLightSensor", 
				10, 
				20, 
				1000);
		
		tempDTO = new SensorDTO();
		tempDTO.setId(5l);
		tempDTO.setName("TemperatureSensor");
		tempDTO.setType("Temperature");
		
		lightDTO = new SensorDTO();
		lightDTO.setId(6l);
		lightDTO.setName("LightSensor");
		lightDTO.setType("Light");
		
		tempDummyDTO = new DummySensorDTO();
		tempDummyDTO.setId(7l);
		tempDummyDTO.setName("DummyTemperatureSensor");
		tempDummyDTO.setType("Temperature");
		tempDummyDTO.setMin(20);
		tempDummyDTO.setMax(25);
		tempDummyDTO.setInterval(1000);
		
		lightDummyDTO = new DummySensorDTO();
		lightDummyDTO.setId(8l);
		lightDummyDTO.setName("DummyLightSensor");
		lightDummyDTO.setType("Light");
		lightDummyDTO.setMin(10);
		lightDummyDTO.setMax(20);
		lightDummyDTO.setInterval(1000);
		
		fakeDTO = new SensorDTO();
		fakeDTO.setType("Wrong SensorType");
		
	}

	@Test
	public void testToVO() {
		try {
			SensorVO newVo = SensorConverter.toVO(tempDTO);
			
			Assert.assertEquals("Not matching IDs!", tempVO.getId(), newVo.getId());
			
			Assert.assertEquals("Not matching names!", tempVO.getName(), newVo.getName());
			
			Assert.assertEquals("Not matching SensorType!", tempVO.getType(), newVo.getType());
			
			newVo = SensorConverter.toVO(lightDTO);
			
			Assert.assertEquals("Not matching IDs!", lightVO.getId(), newVo.getId());
			
			Assert.assertEquals("Not matching names!", lightVO.getName(), newVo.getName());
			
			Assert.assertEquals("Not matching SensorType!", lightVO.getType(), newVo.getType());
			
		} catch (SensorConvertException e) {
			fail();
		}
		
	}
	
	@Test
	public void testToDummyVO() {
		
		try {
			SensorVO newDummyVo = SensorConverter.toVO(tempDummyDTO);
			
			if (!(newDummyVo instanceof DummySensorVO)) {
				fail("Return for DummyDTO isn't DummyVO!");
			} else {
				DummySensorVO dummyVo = (DummySensorVO) newDummyVo;
				
				Assert.assertEquals("Not matching IDs!", tempDummyVO.getId(), dummyVo.getId());
				
				Assert.assertEquals("Not matching names!", tempDummyVO.getName(), dummyVo.getName());
				
				Assert.assertEquals("Not matching SensorType!", tempDummyVO.getType(), dummyVo.getType());
				
				Assert.assertEquals("Not matching lower limit for Dummy!", tempDummyVO.getMinData(), dummyVo.getMinData(), 0.0);
				
				Assert.assertEquals("Not matching upper limit for Dummy!", tempDummyVO.getMaxData(), dummyVo.getMaxData(), 0.0);
				
				Assert.assertEquals("Not matchong update interval for Dummy!", tempDummyVO.getInterval(), dummyVo.getInterval());
				
			} 
			
			newDummyVo = SensorConverter.toVO(lightDummyDTO);
			
			if (!(newDummyVo instanceof DummySensorVO)) {
				fail("Return for DummyDTO isn't DummyVO!");
			} else {
				DummySensorVO dummyVo = (DummySensorVO) newDummyVo;
				
				Assert.assertEquals("Not matching IDs!", lightDummyVO.getId(), dummyVo.getId());
				
				Assert.assertEquals("Not matching names!", lightDummyVO.getName(), dummyVo.getName());
				
				Assert.assertEquals("Not matching SensorType!", lightDummyVO.getType(), dummyVo.getType());
				
				Assert.assertEquals("Not matching lower limit for Dummy!", lightDummyVO.getMinData(), dummyVo.getMinData(), 0.0);
				
				Assert.assertEquals("Not matching upper limit for Dummy!", lightDummyVO.getMaxData(), dummyVo.getMaxData(), 0.0);
				
				Assert.assertEquals("Not matchong update interval for Dummy!", lightDummyVO.getInterval(), dummyVo.getInterval());
				
			} 
			
		} catch (SensorConvertException e) {
			fail();
		}
		
	}
	
	@Test(expected = SensorConvertException.class)
	public void testWrongtypeToVO() throws SensorConvertException {
			SensorVO fakeVo = SensorConverter.toVO(fakeDTO);
	}

	@Test
	public void testToDTO() {
		SensorDTO newDto = SensorConverter.toDTO(tempVO);
		
		Assert.assertEquals("Not matching IDs!", tempDTO.getId(), newDto.getId());
		
		Assert.assertEquals("Not matching names!", tempDTO.getName(), newDto.getName());
		
		Assert.assertEquals("Not matching SensorType!", tempDTO.getType(), newDto.getType());
		
	}
	
	@Test
	public void testToDummyDTO() {
		SensorDTO newDto = SensorConverter.toDTO(tempDummyVO);
		
		if (!(newDto instanceof DummySensorDTO)) {
			fail("Return for DummyVO isn't DummyDTO!");
		} else {
			DummySensorDTO newDummyDto = (DummySensorDTO) newDto;
			
			Assert.assertEquals("Not matching IDs!", tempDummyDTO.getId(), newDummyDto.getId());
			
			Assert.assertEquals("Not matching names!", tempDummyDTO.getName(), newDummyDto.getName());
			
			Assert.assertEquals("Not matching SensorType!", tempDummyDTO.getType(), newDummyDto.getType());
			
			Assert.assertEquals("Not matching lower limit for Dummy!", tempDummyDTO.getMin(), newDummyDto.getMin(), 0.0);
			
			Assert.assertEquals("Not matching upper limit for Dummy!", tempDummyDTO.getMax(), newDummyDto.getMax(), 0.0);
			
			Assert.assertEquals("Not matchong update interval for Dummy!", tempDummyDTO.getInterval(), newDummyDto.getInterval());
			
		}
	}

}
