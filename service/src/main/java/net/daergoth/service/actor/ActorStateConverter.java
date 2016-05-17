package net.daergoth.service.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;
import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

/**
 * Converter between {@code ActorStateDTO} and {@code ActorStateVO}.
 * Methods of this class should only be used in Service layer.
 *
 * @see net.daergoth.coreapi.actor.ActorStateDTO
 * @see net.daergoth.serviceapi.actors.states.ActorStateVO
 */
public class ActorStateConverter {
	
	/**
	 * Converts an {@code ActorStateVO} to DTO.
	 * @param vo  the VO to convert
	 * @return {@code ActorStateDTO} equivalent to the VO
	 */
	public static ActorStateDTO toDTO(ActorStateVO vo) { 
		ActorStateDTO dto = new ActorStateDTO();
		switch (vo.getType()) {
		case Lamp:
			dto.setType(ActorStateType.LAMP);
			dto.setValue(vo.getData());
			break;
		case Thermostat:
			dto.setType(ActorStateType.THERMOSTAT);
			dto.setValue(vo.getData());
			break;
		default:
			return null;
		}
		return dto;
	}
	
	/**
	 * Converts a list of {@code ActorStateVO}s to a list if DTOs.
	 * @param voList  the list of VOs to convert
	 * @return list of {@code ActorStateDTO}s equivalent to the VOs
	 */
	public static List<ActorStateDTO> toDTOs(List<ActorStateVO> voList) {
		List<ActorStateDTO> dtos = new ArrayList<>();
		for (ActorStateVO v : voList) {
			dtos.add(ActorStateConverter.toDTO(v));
		}
		return dtos;
	}
	
	/**
	 * Converts an {@code ActorStateDTO} to VO.
	 * @param dto  the DTO to convert
	 * @return {@code ActorStateVO} equivalent to the DTO
	 */
	public static ActorStateVO toVO(ActorStateDTO dto) {
		switch (dto.getType()) {
		case LAMP:
			LampActorStateVO lampState = new LampActorStateVO();
			lampState.setStatus(dto.getValue()==1);
			lampState.setType(ActorType.Lamp);
			return lampState;
			//break;
		case THERMOSTAT:
			ThermostatActorStateVO thermoState = new ThermostatActorStateVO();
			thermoState.setTargetTemperature(dto.getValue());
			thermoState.setType(ActorType.Thermostat);
			return thermoState;
			//break;
		default:
			return null;
		}
	}
	
	/**
	 * Converts a list of {@code ActorStateDTO}s to a list of VOs.
	 * @param dtoList  the list of DTOs to convert
	 * @return list of {@code ActorStateVO}s equivalent to the DTOs
	 */
	public static List<ActorStateVO> toVOs (List<ActorStateDTO> dtoList) {
		List<ActorStateVO> vos = new ArrayList<>();
		for (ActorStateDTO d : dtoList) {
			vos.add(ActorStateConverter.toVO(d));
		}
		return vos;
	}
	
}
