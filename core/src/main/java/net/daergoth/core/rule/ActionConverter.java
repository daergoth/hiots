package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.core.actor.ActorConverter;
import net.daergoth.core.actor.ActorStateConverter;
import net.daergoth.coreapi.rule.ActionDTO;

/**
 * Converter between {@code Action} entity and {@code ActionDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 * @see net.daergoth.core.rule.Action
 * @see net.daergoth.coreapi.rule.ActionDTO
 */
public class ActionConverter {
	
	/**
	 * Converts a {@code Action} entity to DTO.
	 * @param entity the entity to convert
	 * @return {@code ActionDTO} equivalent to the entity
	 */
	public static ActionDTO toDTO(Action entity) {
		ActionDTO dto = new ActionDTO();
		dto.setId(entity.getId());
		dto.setActor(ActorConverter.toDTO(entity.getActor()));
		dto.setValue(ActorStateConverter.toDTO(entity.getValue()));
		return dto;
	}
	
	/**
	 * Converts a list of {@code Action} entities to a list of DTOs.
	 * @param entityList the list of entities to convert
	 * @return list of {@code ActionDTO}s equivalent to the entities
	 */
	public static List<ActionDTO> toDTOs(List<Action> entityList) {
		List<ActionDTO> dtos = new ArrayList<>();
		for (Action e : entityList) {
			dtos.add(ActionConverter.toDTO(e));
		}
		return dtos;
	}
	
	/**
	 * Converts a {@code ActionDTO} to entity.
	 * @param dto the DTO to convert
	 * @return {@code Action} entity equivalent to the DTO
	 */
	public static Action toEntity(ActionDTO dto) {
		Action entity = new Action();
		entity.setId(dto.getId());
		entity.setActor(ActorConverter.toEntity(dto.getActor()));
		entity.setValue(ActorStateConverter.toEntity(dto.getValue()));
		return entity;
	}
	
	/**
	 * Converts a list of {@code ActionDTO}s to a list of entities.
	 * @param dtoList the list of DTOs to convert
	 * @return list of {@code Action} entities equivalent to the DTOs
	 */
	public static List<Action> toEntities(List<ActionDTO> dtoList) {
		List<Action> entities = new ArrayList<>();
		for (ActionDTO d : dtoList) {
			entities.add(ActionConverter.toEntity(d));
		}
		return entities;
	}
}
