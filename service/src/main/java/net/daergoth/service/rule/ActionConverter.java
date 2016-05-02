package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.service.actor.ActorConverter;
import net.daergoth.service.actor.ActorStateConverter;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.rule.ActionVO;

@Stateless
@LocalBean
public class ActionConverter {
	
	@EJB
	ActorContainerLocal actorContainer;
	
	public ActionVO toVO(ActionDTO d) {
		ActionVO vo = new ActionVO();
		vo.setId(d.getId());
		//vo.setActor(actorContainer.getActors().stream().filter(a -> a.getId() == d.getActor().getId()).findFirst().get());
		
		try {
			vo.setActor(ActorConverter.toVO(d.getActor()));
		} catch (ActorConvertException e) {
			e.printStackTrace();
		}
		
		
		vo.setValue(ActorStateConverter.toVO(d.getValue()));
		return vo;
	}
	
	public List<ActionVO> toVOs(List<ActionDTO> ds) {
		List<ActionVO> vos = new ArrayList<>();
		for (ActionDTO d : ds) {
			vos.add(toVO(d));
		}
		return vos;
	}
	
	public ActionDTO toDTO(ActionVO v) {
		ActionDTO dto = new ActionDTO();
		dto.setId(v.getId());
		dto.setActor(ActorConverter.toDTO(v.getActor()));
		dto.setValue(ActorStateConverter.toDTO(v.getValue()));
		return dto;
	}
	
	public List<ActionDTO> toDTOs(List<ActionVO> vs) {
		List<ActionDTO> dtos = new ArrayList<>();
		for (ActionVO v : vs) {
			dtos.add(toDTO(v));
		}
		return dtos;
	}	
	
}
