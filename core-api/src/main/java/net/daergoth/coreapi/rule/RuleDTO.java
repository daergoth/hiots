package net.daergoth.coreapi.rule;

import java.io.Serializable;
import java.util.List;

public class RuleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320473883280317106L;
	
	private Long id;
	
	private String name;
	
	private boolean enabled;
	
	private List<ConditionDTO> conditions;
	
	private List<ActionDTO> actions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<ConditionDTO> getConditions() {
		return conditions;
	}

	public void setConditions(List<ConditionDTO> conditions) {
		this.conditions = conditions;
	}

	public List<ActionDTO> getActions() {
		return actions;
	}

	public void setActions(List<ActionDTO> actions) {
		this.actions = actions;
	}
	
}
