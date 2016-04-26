package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.rule.RuleDTO;

public class RuleConverter {
	
	public static RuleDTO toDTO(Rule e) {
		RuleDTO dto = new RuleDTO();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setEnabled(e.isEnabled());
		dto.setConditions(ConditionConverter.toDTOs(e.getConditions()));
		dto.setActions(ActionConverter.toDTOs(e.getActions()));
		return dto;
	}
	
	public static List<RuleDTO> toDTOs(List<Rule> es) {
		List<RuleDTO> dtos = new ArrayList<>();
		for (Rule e : es) {
			dtos.add(RuleConverter.toDTO(e));
		}
		return dtos;
	}
	
	public static Rule toEntity(RuleDTO d) {
		Rule entity = new Rule();
		entity.setId(d.getId());
		entity.setName(d.getName());
		entity.setEnabled(d.isEnabled());
		entity.setConditions(ConditionConverter.toEntities(d.getConditions()));
		entity.setActions(ActionConverter.toEntities(d.getActions()));
		return entity;
	}
	
	public static List<Rule> toEntities(List<RuleDTO> ds) {
		List<Rule> entities = new ArrayList<>();
		for (RuleDTO d : ds) {
			entities.add(RuleConverter.toEntity(d));
		}
		return entities;
	}
	
}
