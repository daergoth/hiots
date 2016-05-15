package net.daergoth.core.monitor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.core.actor.ActorConverter;
import net.daergoth.core.sensor.SensorConverter;
import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;

/**
 * Converter between {@code OverviewLayoutElement} entity and {@code OverviewLayoutElementDTO}.
 * Methods of this class should only be used in the Core layer.
 *
 * @see net.daergoth.core.monitor.OverviewLayoutElement
 * @see net.daergoth.coreapi.monitor.OverviewLayoutElementDTO
 */
public class OverviewLayoutElementConverter {
	
	/**
	 * Converts a {@code OverviewLayoutElement} entity to DTO.
	 * @param entity the entity to convert
	 * @return {@code OverviewLayoutElementDTO} equivalent to the entity
	 */
	public static OverviewLayoutElementDTO toDTO(OverviewLayoutElement entity) {
		OverviewLayoutElementDTO dto = new OverviewLayoutElementDTO();
		
		dto.setId(entity.getId());
		dto.setType(entity.getType());
		dto.setActor(ActorConverter.toDTO(entity.getActor()));
		dto.setSensor(SensorConverter.toDTO(entity.getSensor()));
		dto.setColumn(entity.getColumn());
		dto.setRow(entity.getRow());
		
		return dto;
	}
	
	/**
	 * Converts a list of {@code OverviewLayoutElement} entities to a list of DTOs.
	 * @param entityList list of the entities to convert
	 * @return list of {@code OverviewLayoutElementDTO}s equivalent to the entities
	 */
	public static List<OverviewLayoutElementDTO> toDTOs(List<OverviewLayoutElement> entityList) {
		List<OverviewLayoutElementDTO> dtos = new ArrayList<>();
		
		for (OverviewLayoutElement entity : entityList) {
			dtos.add(OverviewLayoutElementConverter.toDTO(entity));
		}
		
		return dtos;
	}

	/**
	 * Converts a {@code OverviewLayoutElementDTO} to entity.
	 * @param dto the DTO to convert
	 * @return {@code OverviewLayoutElement} entity equivalent to the DTO
	 */
	public static OverviewLayoutElement toEntity(OverviewLayoutElementDTO dto) {
		OverviewLayoutElement entity = new OverviewLayoutElement();
		
		entity.setId(dto.getId());
		entity.setType(dto.getType());
		entity.setActor(ActorConverter.toEntity(dto.getActor()));
		entity.setSensor(SensorConverter.toEntity(dto.getSensor()));
		entity.setColumn(dto.getColumn());
		entity.setRow(dto.getRow());
		
		return entity;
	}

	/**
	 * Converts a list of {@code OverviewLayoutElementDTO}s to a list of entities.
	 * @param dtoList list of the DTOs to convert
	 * @return list of {@code OverviewLayoutElement} entities equivalent to the DTOs
	 */
	public static List<OverviewLayoutElement> toEntities(List<OverviewLayoutElementDTO> dtoList) {
		List<OverviewLayoutElement> entities = new ArrayList<>();
		
		for (OverviewLayoutElementDTO dto : dtoList) {
			entities.add(OverviewLayoutElementConverter.toEntity(dto));
		}
		
		return entities;
	}
}
