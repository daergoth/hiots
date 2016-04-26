package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.core.actor.ActorConverter;
import net.daergoth.core.actor.ActorStateConverter;
import net.daergoth.coreapi.rule.ActionDTO;

public class ActionConverter {
	
	public static ActionDTO toDTO(Action e) {
		ActionDTO dto = new ActionDTO();
		dto.setId(e.getActorId());
		dto.setActor(ActorConverter.toDTO(e.getActor()));
		dto.setValue(ActorStateConverter.toDTO(e.getValue()));
		return dto;
	}
	
	public static List<ActionDTO> toDTOs(List<Action> es) {
		List<ActionDTO> dtos = new ArrayList<>();
		for (Action e : es) {
			dtos.add(ActionConverter.toDTO(e));
		}
		return dtos;
	}
	
	public static Action toEntity(ActionDTO d) {
		Action entity = new Action();
		entity.setId(d.getId());
		entity.setActor(ActorConverter.toEntity(d.getActor()));
		entity.setValue(ActorStateConverter.toEntity(d.getValue()));
		return entity;
	}
	
	public static List<Action> toEntities(List<ActionDTO> ds) {
		List<Action> entities = new ArrayList<>();
		for (ActionDTO d : ds) {
			entities.add(ActionConverter.toEntity(d));
		}
		return entities;
	}
}
