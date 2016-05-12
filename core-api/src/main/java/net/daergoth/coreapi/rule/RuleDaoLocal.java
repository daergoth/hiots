package net.daergoth.coreapi.rule;

import java.util.List;

/**
 * Provides access for the database for obtaining {@code Rule} objects.
 * 
 * @see net.daergoth.coreapi.rule.RuleDTO
 */
public interface RuleDaoLocal {
	
	/**
	 * Obtains all {@code Rule}s from the database.
	 * @return the list of rules
	 */
	public List<RuleDTO> getRules();
	
	/**
	 * Persists a {@code Rule} by writing it into the database.
	 * @param rule the rule to persist
	 */
	public void addRule(RuleDTO rule);
	
	/**
	 * Updates a {@code Rule}'s data in the database.
	 * @param rule the rule to update
	 */
	public void updateRule(RuleDTO rule);
	
	/**
	 * Deletes the {@code Rule} with specified ID from the database.
	 * @param id the ID of the rule that will be deleted
	 */
	public void deleteRule(Long id);
	
}
