package net.daergoth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorDaoLocal;
import net.daergoth.serviceapi.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;

@Stateless
@Local(ActorContainerLocal.class)
public class ActorContainerLocalImpl implements ActorContainerLocal{
	
	@EJB
	ActorDaoLocal actorDao;
	
	List<ActorVO> actors = new ArrayList<>();
	
	private boolean changed = true;

	@Override
	public List<ActorVO> getActors() {
		if (changed) {
			List<ActorDTO> dtoList = actorDao.getActors();
			List<ActorVO> voList = new ArrayList<>();
			
			for (ActorDTO dto : dtoList) {
				try {
					voList.add(ActorConverter.toVO(dto));
				} catch (ActorConvertException e) {
					e.printStackTrace();
				}
			}
			
			/*
			for (ActorDTO actorDTO: dtoList) {
				switch(actorDTO.getType()) {
				case "Lamp":
					if (actorDTO.getClass().equals(DummyActorDTO.class)) {
						DummyActorDTO dummyDTO = (DummyActorDTO) actorDTO;
						DummyLampActorVO dummyVO = new DummyLampActorVO();
						dummyVO.setId(dummyDTO.getId());
						dummyVO.setName(dummyDTO.getName());
						voList.add(dummyVO);
					} else {
						LampActorVO actorVO = new LampActorVO();
						actorVO.setId(actorDTO.getId());
						actorVO.setName(actorDTO.getName());
						voList.add(actorVO);
					}
					break;
				case "Thermostat":
					if (actorDTO.getClass().equals(DummyActorDTO.class)) {
						DummyActorDTO dummyDTO = (DummyActorDTO) actorDTO;
						DummyThermostatActorVO dummyVO = new DummyThermostatActorVO();
						dummyVO.setId(dummyDTO.getId());
						dummyVO.setName(dummyDTO.getName());
						voList.add(dummyVO);
					} else {
						ThermostatActorVO actorVO = new ThermostatActorVO();
						actorVO.setId(actorDTO.getId());
						actorVO.setName(actorDTO.getName());
						voList.add(actorVO);
					}
					break;
				}
			}
			*/
			actors = voList;
			
			changed = false;
		} 
		
		return actors;
		
	}

	@Override
	public List<DummyActorVO> getDummyActors() {
		List<DummyActorVO> dummyList = new ArrayList<>();
		
		for (ActorVO actor : getActors()) {
			if (actor.getClass().getSuperclass().equals(DummyActorVO.class)) {
				dummyList.add((DummyActorVO) actor);
			}
		}
		
		return dummyList;
	}

	@Override
	public void addActor(ActorVO a) {
		changed = true;
		
		actorDao.addActor(ActorConverter.toDTO(a));
		/*
		if (a.getClass().getSuperclass().equals(DummyActorVO.class)) {
			DummyActorVO da = (DummyActorVO) a;
			DummyActorDTO newDummyDTO = new DummyActorDTO();
			newDummyDTO.setId(da.getId());
			newDummyDTO.setName(da.getName());
			newDummyDTO.setType(da.getType().toString());
			actorDao.addActor(newDummyDTO);
			
		} else {
			ActorDTO newActorDTO = new ActorDTO();
			newActorDTO.setId(a.getId());
			newActorDTO.setName(a.getName());
			newActorDTO.setType(a.getType().toString());
			actorDao.addActor(newActorDTO);
		}
		*/
	}

	@Override
	public void updateActor(ActorVO a) {
		changed = true;
		
		actorDao.updateActor(ActorConverter.toDTO(a));
	}

	@Override
	public void deleteActor(long id) {
		changed = true;
		
		actorDao.deleteActor(id);
	}

	@Override
	public void deleteActors(List<Long> ids) {
		changed = true;
		
		actorDao.deleteActors(ids);
	}

	@Override
	public long getNewId() {
		try {
			return getActors().stream().mapToLong(s -> s.getId()).max().getAsLong() + 1;
		} catch (NoSuchElementException e) {
			return 1;
		}
	}

}
