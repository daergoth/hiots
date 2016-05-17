package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.ConditionTypeCore;
import net.daergoth.service.sensor.SensorConverter;
import net.daergoth.service.sensor.SensorDataConverter;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

/**
 * Converter between {@code ConditionDTO} and {@code ConditionVO}.
 * Methods of this class should only be used in Service layer.
 * 
 * @see net.daergoth.coreapi.rule.ConditionDTO
 * @see net.daergoth.serviceapi.rule.ConditionVO
 */
@Stateless
public class ConditionConverter {
	
	/**
	 * Converts a {@code ConditionDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code ConditionVO} equivalent to the DTO
	 * @throws SensorConvertException if the {@code Sensor} related to the {@code Condition} has invalid type
	 */
	public static ConditionVO toVO(ConditionDTO dto) throws SensorConvertException  {
		ConditionVO vo = new ConditionVO();
		vo.setId(dto.getId());
		vo.setType(ConditionTypeService.valueOf(dto.getConditionType().toString()));
		vo.setSensor(SensorConverter.toVO(dto.getSensor()));
		vo.setValue(SensorDataConverter.toVO(dto.getValue()));
		return vo;
	}
	
	/**
	 * Converts a list of {@code ConditionDTO}s to VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code ConditionVO}s equivalent to the DTOs
	 * @throws SensorConvertException if a {@code Sensor} related to one of the {@code Condition}s has invalid type
	 */
	public static List<ConditionVO> toVOs(List<ConditionDTO> dtoList) throws SensorConvertException {
		List<ConditionVO> vos = new ArrayList<>();
		for (ConditionDTO d : dtoList) {
			vos.add(toVO(d));
		}
		return vos;
	}
	
	/**
	 * Converts a {@code ConditionVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code ConditionDTO} equivalent to the VO
	 */
	public static ConditionDTO toDTO(ConditionVO vo) {
		ConditionDTO dto = new ConditionDTO();
		dto.setId(vo.getId());
		dto.setConditionType(ConditionTypeCore.valueOf(vo.getType().toString()));
		dto.setSensor(SensorConverter.toDTO(vo.getSensor()));
		dto.setValue(SensorDataConverter.toDTO(vo.getValue()));
		return dto;
	}
	
	/**
	 * Converts a list of {@code ConditionVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code ConditionDTO}s equivalent to the VOs
	 */
	public static List<ConditionDTO> toDTOs(List<ConditionVO> voList) {
		List<ConditionDTO> dtos = new ArrayList<>();
		for (ConditionVO v : voList) {
			dtos.add(toDTO(v));
		}
		return dtos;
	}

}
