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

public class OverviewLayoutElementConverter {

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
	
	public static List<OverviewLayoutElementVO> toVOs(List<OverviewLayoutElementDTO> dtoList) throws ActorConvertException, SensorConvertException {
		List<OverviewLayoutElementVO> vos = new ArrayList<>();
		
		for (OverviewLayoutElementDTO dto : dtoList) {
			vos.add(OverviewLayoutElementConverter.toVO(dto));
		}
		
		return vos;
	}
	
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
	
	public static List<OverviewLayoutElementDTO> toDTOs(List<OverviewLayoutElementVO> voList) {
		List<OverviewLayoutElementDTO> dtos = new ArrayList<>();
		
		for (OverviewLayoutElementVO vo : voList) {
			dtos.add(OverviewLayoutElementConverter.toDTO(vo));
		}
		
		return dtos;
	}
	
}
