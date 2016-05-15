package net.daergoth.serviceapi.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a {@code Rule} in the Rule service.
 * Every {@code Rule} has a list of {@code Condition}s
 * and a list of {@code Action}s.
 * For more information check {@link RuleManagerServiceLocal}.
 *
 * @see net.daergoth.serviceapi.rule.RuleManagerServiceLocal
 * @see net.daergoth.serviceapi.rule.ConditionVO
 * @see net.daergoth.serviceapi.rule.ActionVO
 */
public class RuleVO {
	
	private Long id;
	
	private String name;
	
	private boolean enabled;
	
	private List<ConditionVO> conditions = new ArrayList<>();
	
	private List<ActionVO> actions = new ArrayList<>();
	
	/**
	 * Adds a {@code Condition} to the {@code Rule}.
	 * @param cond  the condition to add to the rule
	 */
	public void addCondition(ConditionVO cond) {
		conditions.add(cond);
	}
	
	/**
	 * Deletes a {@code Condition} from the {@code Rule}.
	 * @param cond  the condition to delete from the rule
	 */
	public void deleteCondition(ConditionVO cond) {
		conditions.remove(cond);
	}
	
	/**
	 * Adds an {@code Action} to the {@code Rule}.
	 * @param action  the action to add to the rule
	 */
	public void addAction(ActionVO action) {
		actions.add(action);
	}
	
	/**
	 * Deletes an {@code Action} from the {@code Rule}.
	 * @param action  the action to delete from the rule
	 */
	public void deleteAction(ActionVO action) {
		actions.remove(action);
	}

	/**
	 * Getter for the {@code Rule}'s ID. 
	 * @return the ID of the rule
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Rule}'s ID. 
	 * @param id  the new ID for the rule
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Rule}'s name.
	 * @return the name of the rule
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the {@code Rule}'s name.
	 * @param name  the new name for the rule
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the {@code Rule}'s status.
	 * @return the status of the rule
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Setter for the {@code Rule}'s status.
	 * @param enabled  the new status for the rule
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Getter for the {@code Rule}'s {@code Condition} list.
	 * @return the list of conditions
	 */
	public List<ConditionVO> getConditions() {
		return conditions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Condition} list.
	 * @param conditions  the new of conditions
	 */
	public void setConditions(List<ConditionVO> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Getter for the {@code Rule}'s {@code Action} list.
	 * @return the list of actions
	 */
	public List<ActionVO> getActions() {
		return actions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Action} list.
	 * @param actions  the new list of actions
	 */
	public void setActions(List<ActionVO> actions) {
		this.actions = actions;
	}
	
}
