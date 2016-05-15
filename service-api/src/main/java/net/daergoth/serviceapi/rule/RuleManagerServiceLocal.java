package net.daergoth.serviceapi.rule;

import java.util.List;

/**
 * Rules service for automating {@code Actor} settings 
 * depending on {@code Sensor} measurements.
 * Every {@code Rule} is activated when ALL the condition are met.
 * This is 
 * {@code Rules} can be created with 'IF-THEN' format,
 * {@code Condition}s making up the 'IF' part of the rule and
 * {@code Action}s the 'THEN' part.
 * <p>
 * Usage:
 * <pre>
 * ...
 * RuleManagerServiceLocal rulesManager;
 * 
 * ConditionVO condition = new ConditionVO();
 * // Here you have to set up the condition
 * 
 * ActionVO action = new ActionVO();
 * // Here you have to set up the action
 * 
 * RuleVO rule = new RuleVO();
 * rule.setName('Example rule');
 * rule.addCondition(condition);
 * rule.addAction(action);
 * 
 * rulesManager.addRule(rule);
 * ...
 * </pre>
 * 
 * 
 * @see net.daergoth.serviceapi.rule.RuleVO
 * @see net.daergoth.serviceapi.rule.ConditionVO
 * @see net.daergoth.serviceapi.rule.ActionVO
 */
public interface RuleManagerServiceLocal {
	 
	/**
	 * Get all {@code Rule}s from the database.
	 * @return the list of rules
	 */
	public List<RuleVO> getRules();
	
	/**
	 * Adds a {@code Rule} to the database.
	 * @param rule  the rule to persist
	 */
	public void addRule(RuleVO rule);
	
	/**
	 * Updates a {@code Rule} in the database.
	 * @param rule  the rule to update
	 */
	public void updateRule(RuleVO rule);
	
	/**
	 * Deletes a {@code Rule} from the database.
	 * @param id  the ID of the rule to be deleted
	 */
	public void deleteRule(Long id);
}
