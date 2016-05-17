package net.daergoth.service.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.SensorDataDTO;
import net.daergoth.coreapi.sensor.SensorDataType;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

/**
 * Converter between {@code SensorDataDTO} and {@code SensorDataVO}.
 * Methods of this class should only be used in Service layer.
 * 
 * @see net.daergoth.coreapi.sensor.SensorDataDTO
 * @see net.daergoth.serviceapi.sensors.datatypes.SensorDataVO
 */
public class SensorDataConverter {
	
	/**
	 * Converts a {@code SensorDataDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code SensorDataVO} equivalent to the DTO
	 */
	public static SensorDataVO toVO(SensorDataDTO dto) {
		SensorDataVO vo;
		switch (dto.getType()) {
		case LIGHT:
			vo = new LightDataVO(dto.getValue());
			break;
		case TEMPERATURE:
			vo = new TemperatureDataVO(dto.getValue());
			break;
		default:
			return null;
		}
		return vo;
	}
	
	/**
	 * Converts a list of {@code SensorDataDTO}s to VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code SensorDataVO}s equivalent to the DTOs
	 */
	public static List<SensorDataVO> toVOs(List<SensorDataDTO> dtoList) {
		List<SensorDataVO> vos = new ArrayList<>();
		for (SensorDataDTO d : dtoList) {
			vos.add(SensorDataConverter.toVO(d));
		}
		return vos;
	}
	
	/**
	 * Converts a {@code SensorDataVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code SensorDataDTO} equivalent to the VO
	 */
	public static SensorDataDTO toDTO(SensorDataVO vo) {
		SensorDataDTO dto = new SensorDataDTO();
		switch (vo.getType()) {
		case Light:
			dto.setType(SensorDataType.LIGHT);
			dto.setValue( vo.getData() );
			break;
		case Temperature:
			dto.setType(SensorDataType.TEMPERATURE);
			dto.setValue( vo.getData() );
			break;
		default:
			return null;
		}
		return dto;
	}
	
	/**
	 * Converts a list of {@code SensorDataVO}s to DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code SensorDataDTO}s equivalent to the VOs
	 */
	public static List<SensorDataDTO> toDTOs(List<SensorDataVO> voList) {
		List<SensorDataDTO> dtos = new ArrayList<>();
		for (SensorDataVO v : voList) {
			dtos.add(SensorDataConverter.toDTO(v));
		}
		return dtos;
	}
}
