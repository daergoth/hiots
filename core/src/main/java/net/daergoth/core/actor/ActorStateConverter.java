package net.daergoth.core.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorStateDTO;

public class ActorStateConverter {
	
	public static ActorStateDTO toDTO(ActorState e) {
		ActorStateDTO dto = new ActorStateDTO();
		dto.setType(e.getActorStateType());
		dto.setValue(e.getActorStateValue());
		return dto;
	}
	
	public static List<ActorStateDTO> toDTOs(List<ActorState> es) {
		List<ActorStateDTO> dtos = new ArrayList<>();
		for (ActorState e : es) {
			dtos.add(ActorStateConverter.toDTO(e));
		}
		return dtos;
	}
	
	public static ActorState toEntity(ActorStateDTO d) {
		ActorState entity = new ActorState();
		entity.setActorStateType(d.getType());
		entity.setActorStateValue(d.getValue());
		return entity;
	}
	
	public static List<ActorState> toEntities(List<ActorStateDTO> ds) {
		List<ActorState> entities = new ArrayList<>();
		for (ActorStateDTO d : ds) {
			entities.add(ActorStateConverter.toEntity(d));
		}
		return entities;
	}
	
}
