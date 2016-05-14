package net.daergoth.core.rule;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.daergoth.coreapi.rule.RuleDTO;
import net.daergoth.coreapi.rule.RuleDaoLocal;

/**
 * Implementation of {@code net.daergoth.coreapi.rule.RuleDaoLocal}.
 * 
 * @see net.daergoth.coreapi.rule.RuleDaoLocal
 */
@Stateless
@Local
public class RuleDaoLocalImpl implements RuleDaoLocal{
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RuleDTO> getRules() {
		TypedQuery<Rule> q = em.createNamedQuery("Rule.findAll", Rule.class);
		return RuleConverter.toDTOs(q.getResultList()); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addRule(RuleDTO r) {
		em.merge(RuleConverter.toEntity(r));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateRule(RuleDTO r) {
		em.merge(RuleConverter.toEntity(r));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteRule(Long id) {
		em.remove(em.find(Rule.class, id));
	}

}
