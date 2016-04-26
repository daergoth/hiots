package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.core.sensor.SensorConverter;
import net.daergoth.core.sensor.SensorDataConverter;
import net.daergoth.coreapi.rule.ConditionDTO;

public class ConditionConverter {
	
	public static ConditionDTO toDTO(Condition e) {
		ConditionDTO dto = new ConditionDTO();
		dto.setId(e.getId());
		dto.setConditionType(e.getType());
		dto.setSensor(SensorConverter.toDTO(e.getSensor()));
		dto.setValue(SensorDataConverter.toDTO(e.getValue()));
		return dto;
	}
	
	public static List<ConditionDTO> toDTOs(List<Condition> es) {
		List<ConditionDTO> dtos = new ArrayList<>();
		for (Condition e : es) {
			dtos.add(ConditionConverter.toDTO(e));
		}
		return dtos;
	}
	
	public static Condition toEntity(ConditionDTO d) {
		Condition entity = new Condition();
		entity.setId(d.getId());
		entity.setType(d.getConditionType());
		entity.setSensor(SensorConverter.toEntity(d.getSensor()));
		entity.setValue(SensorDataConverter.toEntity(d.getValue()));
		return entity;
	}
	
	public static List<Condition> toEntities(List<ConditionDTO> ds) {
		List<Condition> entities = new ArrayList<>();
		for (ConditionDTO d : ds) {
			entities.add(ConditionConverter.toEntity(d));
		}
		return entities;
	}

}
