package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.service.actor.ActorConverter;
import net.daergoth.service.actor.ActorStateConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.rule.ActionVO;

/**
 * Converter between {@code ActionDTO} and {@code ActionVO}.
 * Methods of this class should only be used in Service layer.
 *
 * @see net.daergoth.coreapi.rule.ActionDTO
 * @see net.daergoth.serviceapi.rule.ActionVO
 */
@Stateless
public class ActionConverter {
	
	/**
	 * Converts an {@code ActionDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code ActionVO} equivalent to the DTO
	 * @throws ActorConvertException if the {@code Actor} related to the {@code Action} has invalid type
	 */
	public static ActionVO toVO(ActionDTO dto) throws ActorConvertException {
		ActionVO vo = new ActionVO();
		vo.setId(dto.getId());
		vo.setActor(ActorConverter.toVO(dto.getActor()));
		vo.setValue(ActorStateConverter.toVO(dto.getValue()));
		return vo;
	}
	
	/**
	 * Converts a list of {@code ActionDTO}s to VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code ActionVO}s equivalent to the DTOs
	 * @throws ActorConvertException if an {@code Actor} related to on of the {@code Action}s has invalid type
	 */
	public static List<ActionVO> toVOs(List<ActionDTO> dtoList) throws ActorConvertException {
		List<ActionVO> vos = new ArrayList<>();
		for (ActionDTO d : dtoList) {
			vos.add(toVO(d));
		}
		return vos;
	}
	
	/**
	 * Converts an {@code ActionVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code ActionDTO} equivalent to the VO
	 */
	public static ActionDTO toDTO(ActionVO vo) {
		ActionDTO dto = new ActionDTO();
		dto.setId(vo.getId());
		dto.setActor(ActorConverter.toDTO(vo.getActor()));
		dto.setValue(ActorStateConverter.toDTO(vo.getValue()));
		return dto;
	}
	
	/**
	 * Converts a list of {@code ActionVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code ActionDTO}s equivalent to the VOs
	 */
	public static List<ActionDTO> toDTOs(List<ActionVO> voList) {
		List<ActionDTO> dtos = new ArrayList<>();
		for (ActionVO v : voList) {
			dtos.add(toDTO(v));
		}
		return dtos;
	}	
	
}
