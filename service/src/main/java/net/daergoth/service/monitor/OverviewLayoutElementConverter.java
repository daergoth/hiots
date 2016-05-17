package net.daergoth.service.monitor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.monitor.OverviewLayoutElementDTO;
import net.daergoth.service.actor.ActorConverter;
import net.daergoth.service.sensor.SensorConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementType;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

/**
 * Converter between {@code OverviewLayoutElementDTO} and {@code OverviewLayoutElementVO}.
 * Methods of this class should only be used in Service layer.
 *
 * @see net.daergoth.coreapi.monitor.OverviewLayoutElementDTO
 * @see net.daergoth.serviceapi.monitor.OverviewLayoutElementVO
 */
public class OverviewLayoutElementConverter {

	/**
	 * Converts a {@code OverviewLayoutElementDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code OverviewLayoutElementVO} equivalent to the DTO
	 * @throws ActorConvertException if the {@code Actor}, that belongs to this element, has invalid type
	 * @throws SensorConvertException if the {@code Sensor}, that belongs to this element, has invalid type
	 */
	public static OverviewLayoutElementVO toVO(OverviewLayoutElementDTO dto) throws ActorConvertException, SensorConvertException {
		OverviewLayoutElementVO vo = new OverviewLayoutElementVO();
		
		vo.setId(dto.getId());
		vo.setType(OverviewLayoutElementType.valueOf(dto.getType()));
		vo.setActor(ActorConverter.toVO(dto.getActor()));
		vo.setSensor(SensorConverter.toVO(dto.getSensor()));
		vo.setColumn(dto.getColumn());
		vo.setRow(dto.getRow());
		
		return vo;
	}
	
	/**
	 * Converts a list of {@code OverviewLayoutElementDTO}s to VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code OverviewLayoutElementVO}s equivalent to the DTOs
	 * @throws ActorConvertException if the {@code Actor}, that belongs to this element, has invalid type
	 * @throws SensorConvertException if the {@code Sensor}, that belongs to this element, has invalid type
	 */
	public static List<OverviewLayoutElementVO> toVOs(List<OverviewLayoutElementDTO> dtoList) throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutElementVO> vos = new ArrayList<>();
		
		for (OverviewLayoutElementDTO dto : dtoList) {
			vos.add(OverviewLayoutElementConverter.toVO(dto));
		}
		
		return vos;
	}
	
	/**
	 * Converts a {@code OverviewLayoutElementVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code OverviewLayoutElementDTO} equivalent to the VO
	 */
	public static OverviewLayoutElementDTO toDTO(OverviewLayoutElementVO vo) {
		OverviewLayoutElementDTO dto = new OverviewLayoutElementDTO();
		
		dto.setId(vo.getId());
		dto.setType(vo.getType().name());
		dto.setActor(ActorConverter.toDTO(vo.getActor()));
		dto.setSensor(SensorConverter.toDTO(vo.getSensor()));
		dto.setColumn(vo.getColumn());
		dto.setRow(vo.getRow());
		
		return dto;
	}
	
	/**
	 * Converts a list of {@code OverviewLayoutElementVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code OverviewLayoutElementDTO} equivalent to the VOs
	 */
	public static List<OverviewLayoutElementDTO> toDTOs(List<OverviewLayoutElementVO> voList) {
		List<OverviewLayoutElementDTO> dtos = new ArrayList<>();
		
		for (OverviewLayoutElementVO vo : voList) {
			dtos.add(OverviewLayoutElementConverter.toDTO(vo));
		}
		
		return dtos;
	}
	
}
