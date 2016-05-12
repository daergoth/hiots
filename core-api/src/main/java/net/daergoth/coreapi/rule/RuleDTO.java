package net.daergoth.coreapi.rule;

import java.io.Serializable;
import java.util.List;

/**
 * Represents {@code Rule} entities in the Core API.
 * If all of the {@code Condition}s for a {@code Rule} are met
 * the rule's {@code Action}s will be executed.  
 *
 * @see net.daergoth.coreapi.rule.ConditionDTO
 * @see net.daergoth.coreapi.rule.ActionDTO
 */
public class RuleDTO implements Serializable {

	private static final long serialVersionUID = -1320473883280317106L;
	
	private Long id;
	
	private String name;
	
	private boolean enabled;
	
	private List<ConditionDTO> conditions;
	
	private List<ActionDTO> actions;

	/**
	 * Getter for the {@code Rule}'s ID.
	 * @return the ID of the rule
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Rule}'s ID.
	 * @param id the new ID for the rule
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
	 * @param name the new name for the rule
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
	 * @param enabled the new status for the rule
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Getter for the {@code Rule}'s {@code Condition}s.
	 * @return the list of conditions of the rule
	 */
	public List<ConditionDTO> getConditions() {
		return conditions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Condition}s.
	 * @param conditions list of the new conditions for the rule
	 */
	public void setConditions(List<ConditionDTO> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Getter for the {@code Rule}'s {@code Action}s.
	 * @return the list of actions of the rule
	 */
	public List<ActionDTO> getActions() {
		return actions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Action}s.
	 * @param actions list of the new actions for the rule
	 */
	public void setActions(List<ActionDTO> actions) {
		this.actions = actions;
	}
	
}
