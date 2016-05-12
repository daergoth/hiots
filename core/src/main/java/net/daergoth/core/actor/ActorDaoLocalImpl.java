package net.daergoth.core.actor;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorDaoLocal;

/**
 * @inheritDoc
 */
@Stateless
@Local
public class ActorDaoLocalImpl implements ActorDaoLocal {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ActorDTO> getActors() {
		TypedQuery<Actor> q = em.createNamedQuery("Actor.findAll", Actor.class);
		List<Actor> list = q.getResultList();
		
		List<ActorDTO> nlist = ActorConverter.toDTOs(list);
		
		return nlist;
	}

	@Override
	public void addActor(ActorDTO a) {
		em.merge(ActorConverter.toEntity(a));
	}

	@Override
	public void updateActor(ActorDTO a) {
		em.merge(ActorConverter.toEntity(a));
	}

	@Override
	public void deleteActor(long id) {
		em.remove(em.find(Actor.class, id));
	}

	@Override
	public void deleteActors(List<Long> ids) {
		for (Long id : ids) {
			deleteActor(id);
		}
	}

}
