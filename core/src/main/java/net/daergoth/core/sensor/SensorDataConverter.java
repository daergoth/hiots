package net.daergoth.core.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.SensorDataDTO;

public class SensorDataConverter {
	
	public static SensorDataDTO toDTO(SensorData e) {
		SensorDataDTO dto = new SensorDataDTO();
		dto.setType(e.getSensorDataType());
		dto.setValue(e.getSensorDataValue());		
		return dto;
	}
	
	public static List<SensorDataDTO> toDTOs(List<SensorData> es) {
		List<SensorDataDTO> dtos = new ArrayList<>();
		for (SensorData e : es) {
			dtos.add(SensorDataConverter.toDTO(e));
		}
		return dtos;
	}
	
	public static SensorData toEntity(SensorDataDTO d) {
		SensorData entity = new SensorData();
		entity.setSensorDataType(d.getType());
		entity.setSensorDataValue(d.getValue());
		return entity;
	}
	
	public static List<SensorData> toEntities(List<SensorDataDTO> ds) {
		List<SensorData> entities = new ArrayList<>();
		for (SensorDataDTO d : ds) {
			entities.add(SensorDataConverter.toEntity(d));
		}
		return entities;
	}
	
}
