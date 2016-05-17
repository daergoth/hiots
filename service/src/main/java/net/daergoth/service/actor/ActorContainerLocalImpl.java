package net.daergoth.service.actor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorDaoLocal;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;

/**
 * Default implementation of {@link ActorContainerLocal}.
 */
@Singleton(name = "ActorContainer")
@Local(ActorContainerLocal.class)
public class ActorContainerLocalImpl implements ActorContainerLocal{
	
	@EJB
	private ActorDaoLocal actorDao;
	
	private List<ActorVO> actors = new ArrayList<>();
	
	private boolean changed = true;

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addActor(ActorVO a) {
		changed = true;
		
		actorDao.addActor(ActorConverter.toDTO(a));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateActor(ActorVO a) {
		changed = true;
		
		actorDao.updateActor(ActorConverter.toDTO(a));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteActor(long id) {
		changed = true;
		
		actorDao.deleteActor(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteActors(List<Long> ids) {
		changed = true;
		
		actorDao.deleteActors(ids);
	}

}
