package net.daergoth.service.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorDaoLocal;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;

@Singleton(name = "ActorContainer")
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
