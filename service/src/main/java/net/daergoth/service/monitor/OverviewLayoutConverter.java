package net.daergoth.service.monitor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.monitor.OverviewLayoutDTO;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

public class OverviewLayoutConverter {

	public static OverviewLayoutVO toVO(OverviewLayoutDTO dto) throws ActorConvertException, SensorConvertException {
		OverviewLayoutVO vo = new OverviewLayoutVO();
		
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		vo.setElements(OverviewLayoutElementConverter.toVOs(dto.getElements()));
		
		return vo;
	}
	
	public static List<OverviewLayoutVO> toVOs(List<OverviewLayoutDTO> dtoList) throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutVO> vos = new ArrayList<>();
		
		for (OverviewLayoutDTO dto : dtoList) {
			vos.add(OverviewLayoutConverter.toVO(dto));
		}
		
		return vos;
	}
	
	public static OverviewLayoutDTO toDTO(OverviewLayoutVO vo) {
		OverviewLayoutDTO dto = new OverviewLayoutDTO();
		
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		dto.setElements(OverviewLayoutElementConverter.toDTOs(vo.getElements()));
		
		return dto;
	}
	
	public static List<OverviewLayoutDTO> toDTOs(List<OverviewLayoutVO> voList) {
		List<OverviewLayoutDTO> dtos = new ArrayList<>();
		
		for (OverviewLayoutVO vo : voList) {
			dtos.add(OverviewLayoutConverter.toDTO(vo));
		}
		
		return dtos;
	}
	
}
