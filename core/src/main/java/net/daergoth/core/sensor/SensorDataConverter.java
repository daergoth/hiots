package net.daergoth.core.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.SensorDataDTO;

/**
 * Converter between {@code SensorData} entity and {@code SensorDataDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 * @see net.daergoth.core.sensor.SensorData
 * @see net.daergoth.coreapi.sensor.SensorDataDTO
 */
public class SensorDataConverter {
	
	/**
	 * Converts a {@code SensorData} entity to DTO.
	 * @param entity the entity to convert to DTO
	 * @return {@code SensorDataDTO} equivalent to the entity
	 */
	public static SensorDataDTO toDTO(SensorData entity) {
		SensorDataDTO dto = new SensorDataDTO();
		dto.setType(entity.getSensorDataType());
		dto.setValue(entity.getSensorDataValue());		
		return dto;
	}
	
	/**
	 * Converts a list of {@code SensorData} entities to a list of DTOs.
	 * @param entityList the list of entities to convert
	 * @return list of {@code SensorDataDTO}s equivalent to the entities 
	 */
	public static List<SensorDataDTO> toDTOs(List<SensorData> entityList) {
		List<SensorDataDTO> dtos = new ArrayList<>();
		for (SensorData e : entityList) {
			dtos.add(SensorDataConverter.toDTO(e));
		}
		return dtos;
	}
	
	/**
	 * Converts a {@code SensorDataDTO} to an entity.
	 * @param dto the DTO to convert to entity
	 * @return {@code SensorData} entity equivalent to the DTO
	 */
	public static SensorData toEntity(SensorDataDTO dto) {
		SensorData entity = new SensorData();
		entity.setSensorDataType(dto.getType());
		entity.setSensorDataValue(dto.getValue());
		return entity;
	}
	
	/**
	 * Converts a list of {@code SensorDataDTO}s to a list of entities.
	 * @param dtoList the list of DTOs to convert to a list of entities
	 * @return list of {@code SensorData} entities equivalent to the DTOs
	 */
	public static List<SensorData> toEntities(List<SensorDataDTO> dtoList) {
		List<SensorData> entities = new ArrayList<>();
		for (SensorDataDTO d : dtoList) {
			entities.add(SensorDataConverter.toEntity(d));
		}
		return entities;
	}
	
}
