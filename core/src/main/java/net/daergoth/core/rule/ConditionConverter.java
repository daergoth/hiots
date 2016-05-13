package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.core.sensor.SensorConverter;
import net.daergoth.core.sensor.SensorDataConverter;
import net.daergoth.coreapi.rule.ConditionDTO;

/**
 * Converter between {@code Condition} entity and {@code ConditionDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 * @see net.daergoth.core.rule.Condition
 * @see net.daergoth.coreapi.rule.ConditionDTO
 */
public class ConditionConverter {
	
	/**
	 * Converts a {@code Condition} entity to DTO.
	 * @param entity the entity to convert
	 * @return {@code ConditionDTO} equivalent to the entity
	 */
	public static ConditionDTO toDTO(Condition entity) {
		ConditionDTO dto = new ConditionDTO();
		dto.setId(entity.getId());
		dto.setConditionType(entity.getType());
		dto.setSensor(SensorConverter.toDTO(entity.getSensor()));
		dto.setValue(SensorDataConverter.toDTO(entity.getValue()));
		return dto;
	}
	
	/**
	 * Convert a list of {@code Condition} entities to a list of DTOs.
	 * @param entityList list of the entities to convert
	 * @return list of {@code ConditionDTO}s equivalent to the entities
	 */
	public static List<ConditionDTO> toDTOs(List<Condition> entityList) {
		List<ConditionDTO> dtos = new ArrayList<>();
		for (Condition e : entityList) {
			dtos.add(ConditionConverter.toDTO(e));
		}
		return dtos;
	}
	
	/**
	 * Converts a {@code ConditionDTO} to entity.
	 * @param dto the DTO to convert
	 * @return {@code Condition} entity equivalent to the DTO
	 */
	public static Condition toEntity(ConditionDTO dto) {
		Condition entity = new Condition();
		entity.setId(dto.getId());
		entity.setType(dto.getConditionType());
		entity.setSensor(SensorConverter.toEntity(dto.getSensor()));
		entity.setValue(SensorDataConverter.toEntity(dto.getValue()));
		return entity;
	}
	
	/**
	 * Converts a list of {@code ConditionDTO}s to a list of entities.
	 * @param dtoList list of the DTOs to convert
	 * @return list of {@code Condition} entities equivalent to the DTOs
	 */
	public static List<Condition> toEntities(List<ConditionDTO> dtoList) {
		List<Condition> entities = new ArrayList<>();
		for (ConditionDTO d : dtoList) {
			entities.add(ConditionConverter.toEntity(d));
		}
		return entities;
	}

}
