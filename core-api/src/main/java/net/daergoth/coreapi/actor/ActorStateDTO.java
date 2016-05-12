package net.daergoth.coreapi.actor;

import java.io.Serializable;

/**
 * Represents an {@code Actor}'s state/setting in the Core API.
 * 
 * @see net.daergoth.coreapi.actor.ActorDTO
 */
public class ActorStateDTO implements Serializable {

	private static final long serialVersionUID = -4150500658942319931L;

	private ActorStateType type;
	
	private Double value;

	/**
	 * Getter for {@code ActorState}'s type.
	 * @return the type of the state
	 */
	public ActorStateType getType() {
		return type;
	}

	/**
	 * Setter for {@code ActorState}'s type.
	 * @param type the new type of the state
	 */
	public void setType(ActorStateType type) {
		this.type = type;
	}

	/**
	 * Getter for {@code ActorState}'s type.
	 * @return the value of the state
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Setter for {@code ActorState}'s value.
	 * @param value the new value of the state
	 */
	public void setValue(Double value) {
		this.value = value;
	}

}
