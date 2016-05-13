package net.daergoth.core.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorStateDTO;

/**
 * Converter between {@code ActorState} entity and {@code ActorStateDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 * @see net.daergoth.core.actor.ActorState
 * @see net.daergoth.coreapi.actor.ActorStateDTO
 */
public class ActorStateConverter {
	
	/**
	 * Convert an {@code ActorState} entity to DTO.
	 * @param entity the entity to convert to DTO
	 * @return {@code ActorStateDTO} equivalent to the entity
	 */
	public static ActorStateDTO toDTO(ActorState entity) {
		ActorStateDTO dto = new ActorStateDTO();
		dto.setType(entity.getActorStateType());
		dto.setValue(entity.getActorStateValue());
		return dto;
	}
	
	/**
	 * Convert a list of {@code ActorState} entities to a list of DTOs.
	 * @param entityList the list of entities to convert
	 * @return list of {@code ActorStateDTO}s equivalent to the entities
	 */
	public static List<ActorStateDTO> toDTOs(List<ActorState> entityList) {
		List<ActorStateDTO> dtos = new ArrayList<>();
		for (ActorState e : entityList) {
			dtos.add(ActorStateConverter.toDTO(e));
		}
		return dtos;
	}
	
	/**
	 * Converts an {@code ActorStateDTO} to an entity.
	 * @param dto the DTO to convert to entity
	 * @return {@code ActorState} entity equivalent to the DTO
	 */
	public static ActorState toEntity(ActorStateDTO dto) {
		ActorState entity = new ActorState();
		entity.setActorStateType(dto.getType());
		entity.setActorStateValue(dto.getValue());
		return entity;
	}
	
	/**
	 * Converts a list of {@code ActorStateDTO}s to a list of entities.
	 * @param dtoList the list of DTOs to convert to a list of entities
	 * @return list of {@code ActorState} entities equivalent to the DTOs
	 */
	public static List<ActorState> toEntities(List<ActorStateDTO> dtoList) {
		List<ActorState> entities = new ArrayList<>();
		for (ActorStateDTO d : dtoList) {
			entities.add(ActorStateConverter.toEntity(d));
		}
		return entities;
	}
	
}
