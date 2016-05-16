package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

/**
 * Base class for all types of {@code ActorState}s.
 * Every class that represents an {@code Actor}'s state
 * must extend this class.
 * <p>
 * Also since this is a base for more specific {@code ActorState} classes,
 * you should only use it for inheriting base fields and methods.
 */
public abstract class ActorStateVO {
	
	protected ActorType type;
	
	/**
	 * Gets the data of the {@code ActorState} in human-readable format.
	 * @return the state in human-readable String format
	 */
	public abstract String toString();
	
	/**
	 * Compares another instance of the same type of {@code ActorState} to this.
	 * The comparison results differ between actual {@code ActorState} types.
	 * 
	 * @param other  the state to compare to
	 * @return different between actual {@code ActorState} types
	 * @throws InvalidActorStateTypeException if the two states have different types
	 */
	public abstract int compareTo(ActorStateVO other) throws InvalidActorStateTypeException;
	
	/**
	 * Getter for the {@code ActorState} data.
	 * Meaning of this data is different between actual {@code ActorState} types.
	 * 
	 * @return the data of the state
	 */
	public abstract Double getData();
	
	/**
	 * Setter for the {@code ActorState} data.
	 * Meaning of this data is different between actual {@code ActorState} types.
	 * 
	 * @param data  the new data value for the state
	 */
	public abstract void setData(Double data);

	/**
	 * Getter for the {@code ActorState}'s type.
	 * @return the type of the state
	 */
	public ActorType getType() {
		return type;
	}

	/**
	 * Setter for the {@code ActorState}'s type.
	 * @param type  the new type of the state
	 */
	public void setType(ActorType type) {
		this.type = type;
	}
}
