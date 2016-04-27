package net.daergoth.service.sensor;

import net.daergoth.coreapi.sensor.DummySensorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

public class SensorConverter {
	
	public static SensorVO toVO(SensorDTO dto) throws SensorConvertException {
			
		switch (dto.getType()) {
			case "Temperature":
				if (dto.getClass().equals(DummySensorDTO.class)) {
					DummySensorDTO dummyDTO = (DummySensorDTO) dto;
					DummyTemperatureSensorVO dummyVo = new DummyTemperatureSensorVO(dummyDTO.getId(), 
							dummyDTO.getName(), 
							dummyDTO.getMin(), 
							dummyDTO.getMax(), 
							dummyDTO.getInterval());
					return dummyVo;
				} else {
					TemperatureSensorVO sensorVO = new TemperatureSensorVO(dto.getId(), dto.getName());
					return sensorVO;
				}
			
				//break;
			case "Light":
				if (dto.getClass().equals(DummySensorDTO.class)) {
					DummySensorDTO dummyDTO = (DummySensorDTO) dto;
					DummyLightSensorVO dummyVo = new DummyLightSensorVO(dummyDTO.getId(), 
							dummyDTO.getName(), 
							dummyDTO.getMin(), 
							dummyDTO.getMax(), 
							dummyDTO.getInterval());
					return dummyVo;
				} else {
					LightSensorVO sensorVO = new LightSensorVO(dto.getId(), dto.getName());
					return sensorVO;
				}
				
				//break;
			default:
				throw new SensorConvertException("Unknown type: " + dto.getType());
		}
		
	}
	
	public static SensorDTO toDTO(SensorVO vo) {
		if (vo.getClass().getSuperclass().equals(DummySensorVO.class)) {
			DummySensorVO dvo = (DummySensorVO) vo;
			
			DummySensorDTO newDummyDTO = new DummySensorDTO();
			newDummyDTO.setId(dvo.getId());
			newDummyDTO.setName(dvo.getName());
			newDummyDTO.setType(dvo.getType().toString());
			newDummyDTO.setMax(dvo.getMaxData());
			newDummyDTO.setMin(dvo.getMinData());
			newDummyDTO.setInterval(dvo.getInterval());
			return newDummyDTO;
		} else {
			SensorDTO newSensDTO = new SensorDTO();
			newSensDTO.setId(vo.getId());
			newSensDTO.setName(vo.getName());
			newSensDTO.setType(vo.getType().toString());
			return newSensDTO;
		}
	}
	
}
