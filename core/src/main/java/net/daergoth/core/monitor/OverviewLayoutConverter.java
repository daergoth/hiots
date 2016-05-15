package net.daergoth.core.monitor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.monitor.OverviewLayoutDTO;

/**
 * Converter between {@code OverviewLayout} entity and {@code OverviewLayoutDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 * @see net.daergoth.core.monitor.OverviewLayout
 * @see net.daergoth.coreapi.monitor.OverviewLayoutDTO
 */
public class OverviewLayoutConverter {

	/**
	 * Converts an {@code OverviewLayout} entity to DTO.
	 * @param entity the entity to convert
	 * @return {@code OverviewLayoutDTO} equivalent to the entity
	 */
	public static OverviewLayoutDTO toDTO(OverviewLayout entity) {
		OverviewLayoutDTO dto = new OverviewLayoutDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setElements( OverviewLayoutElementConverter.toDTOs(entity.getElements()) );
		
		return dto;
	}
	
	/**
	 * Converts a list of {@code OverviewLayout} entities to a list of DTOs.
	 * @param entityList list of the entities to convert
	 * @return list of {@code OverviewLayoutDTO}s equivalent to the entities
	 */
	public static List<OverviewLayoutDTO> toDTOs(List<OverviewLayout> entityList) {
		List<OverviewLayoutDTO> dtos = new ArrayList<>();
		
		for(OverviewLayout entity : entityList) {
			dtos.add(OverviewLayoutConverter.toDTO(entity));
		}
		
		return dtos;
	}

	/**
	 * Converts a {@code OverviewLayoutDTO} to entity.
	 * @param dto the DTO to convert
	 * @return {@code OverviewLayout} entity equivalent to the DTO
	 */
	public static OverviewLayout toEntity(OverviewLayoutDTO dto) {
		OverviewLayout entity = new OverviewLayout();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setElements(OverviewLayoutElementConverter.toEntities(dto.getElements()));
		
		return entity;
	}

	/**
	 * Converts a list of {@code OverviewLayoutDTO}s to a list of entities.
	 * @param dtoList list of the DTOs to convert
	 * @return list of {@code OverviewLayout} entities equivalent to the DTOs
	 */
	public static List<OverviewLayout> toEntities(List<OverviewLayoutDTO> dtoList) {
		List<OverviewLayout> entities = new ArrayList<>();
		
		for (OverviewLayoutDTO dto : dtoList) {
			entities.add(OverviewLayoutConverter.toEntity(dto));
		}
		
		return entities;
	}
	
}
