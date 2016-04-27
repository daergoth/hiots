package net.daergoth.service.actor;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.DummyActorDTO;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;

public class ActorConverter {
	
	public static ActorVO toVO(ActorDTO dto) throws ActorConvertException {
		switch(dto.getType()) {
		case "Lamp":
			if (dto.getClass().equals(DummyActorDTO.class)) {
				DummyActorDTO dummyDTO = (DummyActorDTO) dto;
				DummyLampActorVO dummyVO = new DummyLampActorVO(
						dummyDTO.getId(),
						dummyDTO.getName()
				);
				return dummyVO;
			} else {
				LampActorVO actorVO = new LampActorVO(
						dto.getId(),
						dto.getName()
				);
				return actorVO;
			}
			
			//break;
		case "Thermostat":
			if (dto.getClass().equals(DummyActorDTO.class)) {
				DummyActorDTO dummyDTO = (DummyActorDTO) dto;
				DummyThermostatActorVO dummyVO = new DummyThermostatActorVO(
						dummyDTO.getId(),
						dummyDTO.getName()
				);
				return dummyVO;
			} else {
				ThermostatActorVO actorVO = new ThermostatActorVO(
						dto.getId(),
						dto.getName()
				);
				return actorVO;
			}
			
			//break;
		default:
			throw new ActorConvertException("Unknown type: " + dto.getType());
		}
	}
	
	public static ActorDTO toDTO(ActorVO vo) {
		if (vo.getClass().getSuperclass().equals(DummyActorVO.class)) {
			DummyActorVO dvo = (DummyActorVO) vo;
			DummyActorDTO newDummyDTO = new DummyActorDTO();
			newDummyDTO.setId(dvo.getId());
			newDummyDTO.setName(dvo.getName());
			newDummyDTO.setType(dvo.getType().toString());
			return newDummyDTO;
			
		} else {
			ActorDTO newActorDTO = new ActorDTO();
			newActorDTO.setId(vo.getId());
			newActorDTO.setName(vo.getName());
			newActorDTO.setType(vo.getType().toString());
			return newActorDTO;
		}
	}
}
