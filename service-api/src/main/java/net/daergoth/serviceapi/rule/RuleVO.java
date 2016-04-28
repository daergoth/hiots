package net.daergoth.serviceapi.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleVO {
	
	private Long id;
	
	private String name;
	
	private boolean enabled;
	
	private List<ConditionVO> conditions = new ArrayList<>();
	
	private List<ActionVO> actions = new ArrayList<>();
	
	public void addCondition(ConditionVO cond) {
		conditions.add(cond);
	}
	
	public void deleteCondition(ConditionVO cond) {
		conditions.remove(cond);
	}
	
	public void addAction(ActionVO action) {
		actions.add(action);
	}
	
	public void deleteAction(ActionVO action) {
		actions.remove(action);
	}

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

	public List<ConditionVO> getConditions() {
		return conditions;
	}

	public void setConditions(List<ConditionVO> conditions) {
		this.conditions = conditions;
	}

	public List<ActionVO> getActions() {
		return actions;
	}

	public void setActions(List<ActionVO> actions) {
		this.actions = actions;
	}
	
}
