package net.daergoth.coreapi.rule;

import java.util.List;

public interface RuleDaoLocal {
	
	public List<RuleDTO> getRules();
	
	public void addRule(RuleDTO r);
	
	public void updateRule(RuleDTO r);
	
	public void deleteRule(Long id);
	
}
