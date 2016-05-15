package net.daergoth.core.monitor;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.daergoth.coreapi.monitor.OverviewLayoutDTO;
import net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal;

/**
 * Implementation of {@code net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal}.
 *
 * @see net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal
 */
@Stateless
@Local
public class OverviewLayoutDaoLocalImpl implements OverviewLayoutDaoLocal {
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OverviewLayoutDTO> getLayouts() {
		TypedQuery<OverviewLayout> q = em.createNamedQuery("OverviewLayout.findAll", OverviewLayout.class);
		return OverviewLayoutConverter.toDTOs(q.getResultList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLayout(OverviewLayoutDTO layout) {
		em.merge(OverviewLayoutConverter.toEntity(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLayout(OverviewLayoutDTO layout) {
		em.merge(OverviewLayoutConverter.toEntity(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteLayout(Long id) {
		em.remove(em.find(OverviewLayout.class, id));
	}

}
