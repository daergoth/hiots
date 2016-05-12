package net.daergoth.coreapi.rule;

import java.io.Serializable;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorStateDTO;

/**
 * Represents an {@code Action} in a {@code Rule} in the Core API.
 * 
 * @see net.daergoth.coreapi.rule.RuleDTO
 */
public class ActionDTO implements Serializable {

	private static final long serialVersionUID = -8278253653345639623L;
	
	private Long id;
	
	private ActorDTO actor;
	
	private ActorStateDTO value;

	/**
	 * Getter for the {@code Action}'s ID.
	 * @return the ID of the action
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Action}'s ID.
	 * @param id the new ID of the action
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Action}'s {@code Actor} on which the action will be executed.
	 * @return the actor of the action
	 */
	public ActorDTO getActor() {
		return actor;
	}

	/**
	 * Setter for the {@code Action}'s {@code Actor} on which the action will be executed.
	 * @param actor the new actor for the action
	 */
	public void setActor(ActorDTO actor) {
		this.actor = actor;
	}

	/**
	 * Getter for the {@code Action}'s value which the {@code Actor} will be set to.
	 * @return the value of the action
	 */
	public ActorStateDTO getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Action}'s value which the {@code Actor} will be set to.
	 * @param value the new value of the action
	 */
	public void setValue(ActorStateDTO value) {
		this.value = value;
	}
	
	
}
