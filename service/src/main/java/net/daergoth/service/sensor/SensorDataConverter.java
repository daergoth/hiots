package net.daergoth.service.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class SensorDataConverter {
	
	public static SensorDataVO toVO(SensorDataDTO d) {
		SensorDataVO vo;
		switch (d.getType()) {
		case LIGHT:
			vo = new LightDataVO(d.getValue());
			break;
		case TEMPERATURE:
			vo = new TemperatureDataVO(d.getValue());
			break;
		default:
			return null;
		}
		return vo;
	}
	
	public static List<SensorDataVO> toVOs(List<SensorDataDTO> ds) {
		List<SensorDataVO> vos = new ArrayList<>();
		for (SensorDataDTO d : ds) {
			vos.add(SensorDataConverter.toVO(d));
		}
		return vos;
	}
	
	public static SensorDataDTO toDTO(SensorDataVO v) {
		SensorDataDTO dto = new SensorDataDTO();
		SensorDataType type;
		if (v instanceof LightDataVO) {
			type = SensorDataType.LIGHT;
			dto.setValue( ((LightDataVO)v).getLightness() );
		} else if (v instanceof TemperatureDataVO) {
			type = SensorDataType.TEMPERATURE;
			dto.setValue( ((TemperatureDataVO)v).getTemperature() );
		} else {
			return null;
		}
		dto.setType(type);
		return dto;
	}
	
	public static List<SensorDataDTO> toDTOs(List<SensorDataVO> vs) {
		List<SensorDataDTO> dtos = new ArrayList<>();
		for (SensorDataVO v : vs) {
			dtos.add(SensorDataConverter.toDTO(v));
		}
		return dtos;
	}
}
