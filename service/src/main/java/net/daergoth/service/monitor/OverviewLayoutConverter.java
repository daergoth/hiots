package net.daergoth.service.monitor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.monitor.OverviewLayoutDTO;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

/**
 * Converter between {@code OverviewLayoutDTO} and {@code OverviewLayoutVO}.
 * Methods of this class should only be used in Service layer.
 * 
 * @see net.daergoth.coreapi.monitor.OverviewLayoutDTO
 * @see net.daergoth.serviceapi.monitor.OverviewLayoutVO
 */
public class OverviewLayoutConverter {

	/**
	 * Converts a {@code OverviewLayoutDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code OverviewLayoutVO} equivalent to the DTO
	 * @throws ActorConvertException if an {@code Actor}, that belongs to an element of the layout, has invalid type
	 * @throws SensorConvertException if a {@code Sensor}, that belongs to an element of the layout, has invalid type
	 */
	public static OverviewLayoutVO toVO(OverviewLayoutDTO dto) throws ActorConvertException, SensorConvertException {
		OverviewLayoutVO vo = new OverviewLayoutVO();
		
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		vo.setElements(OverviewLayoutElementConverter.toVOs(dto.getElements()));
		
		return vo;
	}
	
	/**
	 * Converts a list of {@code OverviewLayoutDTO}s to a list of VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code OverviewLayoutVO}s equivalent to the DTOs
	 * @throws ActorConvertException if an {@code Actor}, that belongs to an element of one of the layouts, has invalid type
	 * @throws SensorConvertException if a {@code Sensor}, that belongs to an element of one of the layouts, has invalid type
	 */
	public static List<OverviewLayoutVO> toVOs(List<OverviewLayoutDTO> dtoList) throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutVO> vos = new ArrayList<>();
		
		for (OverviewLayoutDTO dto : dtoList) {
			vos.add(OverviewLayoutConverter.toVO(dto));
		}
		
		return vos;
	}
	
	/**
	 * Converts a {@code OverviewLayoutVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code OverviewLayoutDTO} equivalent to the VO
	 */
	public static OverviewLayoutDTO toDTO(OverviewLayoutVO vo) {
		OverviewLayoutDTO dto = new OverviewLayoutDTO();
		
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		dto.setElements(OverviewLayoutElementConverter.toDTOs(vo.getElements()));
		
		return dto;
	}
	
	/**
	 * Converts a list of {@code OverviewLayoutVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code OverviewLayoutDTO}s equivalent to the VOs
	 */
	public static List<OverviewLayoutDTO> toDTOs(List<OverviewLayoutVO> voList) {
		List<OverviewLayoutDTO> dtos = new ArrayList<>();
		
		for (OverviewLayoutVO vo : voList) {
			dtos.add(OverviewLayoutConverter.toDTO(vo));
		}
		
		return dtos;
	}
	
}
