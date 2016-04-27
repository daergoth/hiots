package net.daergoth.serviceapi.rule;

import java.util.List;

public interface RuleManagerServiceLocal {
	 
	public List<RuleVO> getRules();
	
	public void addRule(RuleVO r);
	
	public void updateRule(RuleVO r);
	
	public void deleteRule(Long id);
}
