package net.daergoth.core.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.rule.RuleDTO;

/**
 * Converter between {@code Rule} entity and {@code RuleDTO}.
 * Methods of this class should only be used in the Core layer.
 *
 * @see net.daergoth.core.rule.Rule
 * @see net.daergoth.coreapi.rule.RuleDTO
 */
public class RuleConverter {
	
	/**
	 * Converts a {@code Rule} entity to DTO.
	 * @param entity the entity to convert
	 * @return {@code RuleDTO} equivalent to the entity
	 */
	public static RuleDTO toDTO(Rule entity) {
		RuleDTO dto = new RuleDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEnabled(entity.isEnabled());
		dto.setConditions(ConditionConverter.toDTOs(entity.getConditions()));
		dto.setActions(ActionConverter.toDTOs(entity.getActions()));
		return dto;
	}
	
	/**
	 * Converts a list of {@code Rule} entities to a list of DTOs.
	 * @param entityList list of the entities to convert
	 * @return list of {@code RuleDTO}s equivalent to the entities
	 */
	public static List<RuleDTO> toDTOs(List<Rule> entityList) {
		List<RuleDTO> dtos = new ArrayList<>();
		for (Rule e : entityList) {
			dtos.add(RuleConverter.toDTO(e));
		}
		return dtos;
	}
	
	/**
	 * Converts a {@code RuleDTO} to entity.
	 * @param dto the DTO to convert
	 * @return {@code Rule} entity equivalent to the DTO
	 */
	public static Rule toEntity(RuleDTO dto) {
		Rule entity = new Rule();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEnabled(dto.isEnabled());
		entity.setConditions(ConditionConverter.toEntities(dto.getConditions()));
		entity.setActions(ActionConverter.toEntities(dto.getActions()));
		return entity;
	}
	
	/**
	 * Converts a list of {@code RuleDTO}s to a list of entities.
	 * @param dtoList list of the DTOs to convert
	 * @return list of {@code Rule} entities equivalent to the DTOs
	 */
	public static List<Rule> toEntities(List<RuleDTO> dtoList) {
		List<Rule> entities = new ArrayList<>();
		for (RuleDTO d : dtoList) {
			entities.add(RuleConverter.toEntity(d));
		}
		return entities;
	}
	
}
