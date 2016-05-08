package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.service.actor.ActorConverter;
import net.daergoth.service.actor.ActorStateConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.rule.ActionVO;

@Stateless
public class ActionConverter {
	
	public static ActionVO toVO(ActionDTO d) throws ActorConvertException {
		ActionVO vo = new ActionVO();
		vo.setId(d.getId());
		vo.setActor(ActorConverter.toVO(d.getActor()));
		vo.setValue(ActorStateConverter.toVO(d.getValue()));
		return vo;
	}
	
	public static List<ActionVO> toVOs(List<ActionDTO> ds) throws ActorConvertException {
		List<ActionVO> vos = new ArrayList<>();
		for (ActionDTO d : ds) {
			vos.add(toVO(d));
		}
		return vos;
	}
	
	public static ActionDTO toDTO(ActionVO v) {
		ActionDTO dto = new ActionDTO();
		dto.setId(v.getId());
		dto.setActor(ActorConverter.toDTO(v.getActor()));
		dto.setValue(ActorStateConverter.toDTO(v.getValue()));
		return dto;
	}
	
	public static List<ActionDTO> toDTOs(List<ActionVO> vs) {
		List<ActionDTO> dtos = new ArrayList<>();
		for (ActionVO v : vs) {
			dtos.add(toDTO(v));
		}
		return dtos;
	}	
	
}
