package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.rule.RuleDTO;
import net.daergoth.serviceapi.rule.RuleVO;

public class RuleConverter {
	
	public static RuleVO toVO(RuleDTO d) {
		RuleVO vo = new RuleVO();
		vo.setId(d.getId());
		vo.setName(d.getName());
		vo.setEnabled(d.isEnabled());
		vo.setConditions(new ConditionConverter().toVOs(d.getConditions()));
		vo.setActions(new ActionConverter().toVOs(d.getActions()));
		return vo;
	}
	
	public static List<RuleVO> toVOs(List<RuleDTO> ds) {
		List<RuleVO> vos = new ArrayList<>();
		for (RuleDTO d : ds) {
			vos.add(RuleConverter.toVO(d));
		}
		return vos;
	}
	
	public static RuleDTO toDTO(RuleVO v) {
		RuleDTO dto = new RuleDTO();
		dto.setId(v.getId());
		dto.setName(v.getName());
		dto.setEnabled(v.isEnabled());
		dto.setConditions(new ConditionConverter().toDTOs(v.getConditions()));
		dto.setActions(new ActionConverter().toDTOs(v.getActions()));
		return dto;
	}
	
	public static List<RuleDTO> toDTOs(List<RuleVO> vs) {
		List<RuleDTO> dtos = new ArrayList<>();
		for (RuleVO v : vs) {
			dtos.add(RuleConverter.toDTO(v));
		}
		return dtos;
	}
}
