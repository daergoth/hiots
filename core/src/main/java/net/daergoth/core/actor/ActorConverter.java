package net.daergoth.core.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.DummyActorDTO;

/**
 * Converter between {@code Actor} entity and {@code ActorDTO}.
 * Methods of this class should only be used in Core layer.
 * 
 * @see net.daergoth.core.actor.Actor
 * @see net.daergoth.coreapi.actor.ActorDTO
 */
public class ActorConverter {
	
	/**
	 * Converts an {@code Actor} entity to DTO.
	 * @param entity the entity to convert to DTO
	 * @return {@code ActorDTO} equivalent to the entity
	 */
	public static ActorDTO toDTO(Actor entity) {
		ActorDTO dto = new DummyActorDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		return dto;
	}
	
	/**
	 * Converts a list of {@code Actor} entities to a list of DTOs.
	 * @param entityList list of entities to convert
	 * @return list of {@code ActorDTO}s equivalent to the entities
	 */
	public static List<ActorDTO> toDTOs(List<Actor> entityList) {
		List<ActorDTO> dtos = new ArrayList<>();
		for (Actor actor : entityList) {
			dtos.add(toDTO(actor));
		}
		return dtos;
	}
	
	/**
	 * Converts an {@code Actor} DTO to an entity.
	 * @param dto the DTO to convert to entity
	 * @return {@code Actor} entity equivalent to the DTO
	 */
	public static Actor toEntity(ActorDTO dto) {
		if (dto.getClass().equals(DummyActorDTO.class)) {
			DummyActorDTO da = (DummyActorDTO) dto;
			
			Actor e = new Actor();
			e.setId(da.getId());
			e.setName(da.getName());
			e.setType(da.getType());
			
			return e;
		} else {
			Actor e = new Actor();
			e.setId(dto.getId());
			e.setName(dto.getName());
			e.setType(dto.getType());
			
			return e;
		}
	}
	
	/**
	 * Converts a list of {@code Actor} DTOs to a list of entities.
	 * @param dtoList the list of DTOs to convert to a list of entities
	 * @return list of {@code Actor} entities equivalent to the DTOs
	 */
	public static List<Actor> toEntities(List<ActorDTO> dtoList) {
		List<Actor> entities = new ArrayList<>();
		for (ActorDTO dto : dtoList) {
			entities.add(toEntity(dto));
		}
		return entities;
	}

}
