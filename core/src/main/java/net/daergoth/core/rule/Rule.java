package net.daergoth.core.rule;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JPA Entity class for {@code Rule}s.
 * Each {@code Rule} has a list of {@code Condition}s and a list of {@code Action}s. 
 * All of the actions will be executed, if all conditions are met.
 * 
 * @see net.daergoth.core.rule.Condition
 * @see net.daergoth.core.rule.Action
 */
@Entity
@Table(name = "rules")
@NamedQueries({
	@NamedQuery(name = "Rule.findAll", query = "SELECT r FROM Rule r")
})
public class Rule implements Serializable{

	private static final long serialVersionUID = 3811655697634424417L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private boolean enabled = true;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Condition> conditions;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Action> actions;

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
	 * @param name the new of the rule
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
	 * @return a list of conditions of the rule
	 */
	public List<Condition> getConditions() {
		return conditions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Condition}s.
	 * @param conditions list of the new conditions for the rule
	 */
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	/**
	 * Getter for the {@code Rule}'s {@code Action}s.
	 * @return a list of actions of the rule
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * Setter for the {@code Rule}'s {@code Action}s.
	 * @param actions list of the new actions for the rule
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
}
