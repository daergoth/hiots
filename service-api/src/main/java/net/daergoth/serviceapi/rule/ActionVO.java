package net.daergoth.serviceapi.rule;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.states.ActorStateVO;

/**
 * Represents an {@code Action} in the Rule service.
 * An {@code Action} basically means setting an {@code Actor}
 * to a specific {@code ActorState} (i.e. turning a lamp on).
 * Each action is in relation with a {@code Rule}.
 * 
 * @see net.daergoth.serviceapi.rule.RuleManagerServiceLocal
 * @see net.daergoth.serviceapi.rule.RuleVO
 */
public class ActionVO {
	
	private Long id;
	
	private ActorVO actor;
	
	private ActorStateVO value;

	/**
	 * Getter for the {@code Action}'s ID.
	 * 
	 * @return the ID for the action
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Action}'s ID.
	 * @param id  the new ID for the action
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Actor} on which the {@code Action} will be executed. 
	 * @return the actor of the action
	 */
	public ActorVO getActor() {
		return actor;
	}

	/**
	 * Getter for the {@code Actor} on which the {@code Action} will be executed. 
	 * @param actor  the new actor for the action
	 */
	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	/**
	 * Getter for the {@code Action}'s {@code ActorState}. 
	 * @return the value of the action
	 */
	public ActorStateVO getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Action}'s {@code ActorState}.
	 * @param value  the new value for the action
	 */
	public void setValue(ActorStateVO value) {
		this.value = value;
	}
	
	
	
}
