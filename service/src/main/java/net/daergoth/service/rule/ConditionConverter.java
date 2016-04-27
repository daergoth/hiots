package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.ConditionTypeCore;
import net.daergoth.service.sensor.SensorConverter;
import net.daergoth.service.sensor.SensorDataConverter;
import net.daergoth.serviceapi.rule.ConditionTypeService;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;

public class ConditionConverter {
	
	@EJB
	private static SensorContainerLocal sensorContainer;
	
	public static ConditionVO toVO(ConditionDTO d) {
		ConditionVO vo = new ConditionVO();
		vo.setId(d.getId());
		vo.setType(ConditionTypeService.valueOf(d.getConditionType().toString()));
		vo.setSensor(sensorContainer.getSensors().stream().filter(s -> s.getId() == d.getSensor().getId()).findFirst().get());
		vo.setValue(SensorDataConverter.toVO(d.getValue()));
		return vo;
	}
	
	public static List<ConditionVO> toVOs(List<ConditionDTO> ds) {
		List<ConditionVO> vos = new ArrayList<>();
		for (ConditionDTO d : ds) {
			vos.add(ConditionConverter.toVO(d));
		}
		return vos;
	}
	
	public static ConditionDTO toDTO(ConditionVO v) {
		ConditionDTO dto = new ConditionDTO();
		dto.setId(v.getId());
		dto.setConditionType(ConditionTypeCore.valueOf(v.getType().toString()));
		dto.setSensor(SensorConverter.toDTO(v.getSensor()));
		dto.setValue(SensorDataConverter.toDTO(v.getValue()));
		return dto;
	}
	
	public static List<ConditionDTO> toDTOs(List<ConditionVO> vs) {
		List<ConditionDTO> dtos = new ArrayList<>();
		for (ConditionVO v : vs) {
			dtos.add(ConditionConverter.toDTO(v));
		}
		return dtos;
	}

}
