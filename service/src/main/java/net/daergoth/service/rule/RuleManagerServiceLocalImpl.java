package net.daergoth.service.rule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;

import net.daergoth.coreapi.rule.RuleDaoLocal;
import net.daergoth.serviceapi.rule.RuleManagerServiceLocal;
import net.daergoth.serviceapi.rule.RuleVO;

@Singleton
@Local(RuleManagerServiceLocal.class)
public class RuleManagerServiceLocalImpl implements RuleManagerServiceLocal{
	
	@EJB
	RuleDaoLocal ruleDao;
	
	List<RuleVO> rules = new ArrayList<>();
	
	private boolean changed = true;

	@Override
	public List<RuleVO> getRules() {
		if (changed) {
			rules = RuleConverter.toVOs(ruleDao.getRules());
			changed = false;
		}
		
		return rules;
	}

	@Override
	public void addRule(RuleVO r) {
		changed = true;
		ruleDao.addRule(RuleConverter.toDTO(r));
	}

	@Override
	public void updateRule(RuleVO r) {
		changed = true;
		ruleDao.updateRule(RuleConverter.toDTO(r));
	}

	@Override
	public void deleteRule(Long id) {
		changed = true;
		ruleDao.deleteRule(id);
	}

}
