package net.daergoth.service.actor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

public class ActorStateConverter {
	
	public static ActorStateDTO toDTO(ActorStateVO v) { 
		ActorStateDTO dto = new ActorStateDTO();
		ActorStateType type;
		if (v instanceof LampActorStateVO) {
			type = ActorStateType.LAMP;
			dto.setValue( ((LampActorStateVO)v).getStatus()?1.0:0.0 );
		} else if (v instanceof ThermostatActorStateVO) {
			type = ActorStateType.THERMOSTAT;
			dto.setValue( ((ThermostatActorStateVO)v).getTargetTemperature() );
		} else {
			return null;
		}
		dto.setType(type);
		return dto;
	}
	
	public static List<ActorStateDTO> toDTOs(List<ActorStateVO> vs) {
		List<ActorStateDTO> dtos = new ArrayList<>();
		for (ActorStateVO v : vs) {
			dtos.add(ActorStateConverter.toDTO(v));
		}
		return dtos;
	}
	
	public static ActorStateVO toVO(ActorStateDTO d) {
		switch (d.getType()) {
		case LAMP:
			LampActorStateVO lampState = new LampActorStateVO();
			lampState.setStatus(d.getValue()==1);
			return lampState;
			//break;
		case THERMOSTAT:
			ThermostatActorStateVO thermoState = new ThermostatActorStateVO();
			thermoState.setTargetTemperature(d.getValue());
			return thermoState;
			//break;
		default:
			return null;
		}
	}
	
	public static List<ActorStateVO> toVOs (List<ActorStateDTO> ds) {
		List<ActorStateVO> vos = new ArrayList<>();
		for (ActorStateDTO d : ds) {
			vos.add(ActorStateConverter.toVO(d));
		}
		return vos;
	}
	
}
