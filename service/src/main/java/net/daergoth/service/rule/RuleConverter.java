package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.rule.RuleDTO;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

/**
 * Converter between {@code RuleDTO} and {@code RuleVO}.
 * Methods of this class should only be used in Service layer.
 *
 * @see net.daergoth.coreapi.rule.RuleDTO
 * @see net.daergoth.serviceapi.rule.RuleVO
 */
public class RuleConverter {
	
	/**
	 * Converts a {@code RuleDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code RuleVO} equivalent to the DTO
	 * @throws SensorConvertException if a {@code Sensor} related to this {@code Rule} has invalid type
	 * @throws ActorConvertException if an {@code Actor} related to this {@code Rule} has invalid type
	 */
	public static RuleVO toVO(RuleDTO dto) throws SensorConvertException, ActorConvertException {
		RuleVO vo = new RuleVO();
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		vo.setEnabled(dto.isEnabled());
		vo.setConditions(ConditionConverter.toVOs(dto.getConditions()));
		vo.setActions(ActionConverter.toVOs(dto.getActions()));
		return vo;
	}
	
	/**
	 * Converts a list of {@code RuleDTO}s to VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code RuleVO}s equivalent to the DTOs
	 * @throws SensorConvertException if a {@code Sensor} related to one of the {@code Rule}s has invalid type
	 * @throws ActorConvertException if an {@code Actor} related to one of the {@code Rule}s has invalid type
	 */
	public static List<RuleVO> toVOs(List<RuleDTO> dtoList) throws SensorConvertException, ActorConvertException {
		List<RuleVO> vos = new ArrayList<>();
		for (RuleDTO d : dtoList) {
			vos.add(RuleConverter.toVO(d));
		}
		return vos;
	}
	
	/**
	 * Converts a {@code RuleVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code RuleDTO} equivalent to the VO
	 */
	public static RuleDTO toDTO(RuleVO vo) {
		RuleDTO dto = new RuleDTO();
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		dto.setEnabled(vo.isEnabled());
		dto.setConditions(ConditionConverter.toDTOs(vo.getConditions()));
		dto.setActions(ActionConverter.toDTOs(vo.getActions()));
		return dto;
	}
	
	/**
	 * Converts a list of {@code RuleVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code RuleDTO}s equivalent to the VOs
	 */
	public static List<RuleDTO> toDTOs(List<RuleVO> voList) {
		List<RuleDTO> dtos = new ArrayList<>();
		for (RuleVO v : voList) {
			dtos.add(RuleConverter.toDTO(v));
		}
		return dtos;
	}
}
