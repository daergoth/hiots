package net.daergoth.core.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.DummyActorDTO;

public class ActorConverter {
	
	public static ActorDTO toDTO(Actor a) {

		ActorDTO dto = new ActorDTO();
		dto.setId(a.getId());
		dto.setName(a.getName());
		dto.setType(a.getType());
		return dto;
		
	}
	
	public static List<ActorDTO> toDTOs(List<Actor> as) {
		List<ActorDTO> dtos = new ArrayList<>();
		for (Actor actor : as) {
			dtos.add(toDTO(actor));
		}
		return dtos;
	}
	
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
	
	public static List<Actor> toEntities(List<ActorDTO> dtos) {
		List<Actor> entities = new ArrayList<>();
		for (ActorDTO dto : dtos) {
			entities.add(toEntity(dto));
		}
		return entities;
	}

}
