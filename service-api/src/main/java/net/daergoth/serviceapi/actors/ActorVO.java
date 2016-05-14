package net.daergoth.serviceapi.actors;

import java.io.Serializable;

import net.daergoth.serviceapi.actors.states.ActorStateVO;

/**
 * Base class for all types of {@code Actor}s.
 * Every class that represents a type of an {@code Actor} must extend this class.
 * <p>
 * Please note that {@code DummyActorVO} is also a subclass of this class,
 * and is used for {@code Actor} simulation.
 * <p>
 * Also since this is a base for more specific {@code Actor} classes,
 * you should only use it for inheriting base fields and methods.
 * 
 * @see net.daergoth.serviceapi.actors.dummy.DummyActorVO
 */
public abstract class ActorVO implements Serializable{

	private static final long serialVersionUID = 1183897527108648159L;
	
	protected long Id;
	
	protected String Name;
	
	protected ActorStateVO State;
	
	protected ActorType Type;
	
	/**
	 * Constructs a new type-less {@code Actor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 */
	public ActorVO() {}

	/**
	 * Constructs a new type-less {@code Actor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 * 
	 * @param id the id of the new actor
	 * @param name the name of the new actor
	 */
	public ActorVO(long id, String name) {
		this.Id = id;
		this.Name = name;
	}

	/**
	 * Getter for the {@code Actor}'s ID.
	 * 
	 * @return the ID of the actor
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * Setter for the {@code Actor}'s ID.
	 * 
	 * @param id the new ID for the actor
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * Getter for the {@code Actor}'s name.
	 * 
	 * @return the name of the actor
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Setter for the {@code Actor}'s name.
	 * 
	 * @param name the new name of the actor
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Getter for the {@code Actor}'s current state.
	 * 
	 * @return the current state of the actor
	 */
	public ActorStateVO getState() {
		return State;
	}

	/**
	 * Setter for the {@code Actor}'s current state.
	 * The given {@code ActorState} should have the same {@code ActorType} as the {@code Actor}.
	 * 
	 * @param state the new state for the actor
	 * @throws InvalidActorStateTypeException if {@code ActorState} given has wrong {@code ActorType}
	 */
	public abstract void setState(ActorStateVO state) throws InvalidActorStateTypeException;

	/**
	 * Getter for the {@code Actor}'s type.
	 * 
	 * @return the type of the actor
	 */
	public ActorType getType() {
		return Type;
	}

	/**
	 * Setter for the {@code Actor}'s type.
	 * 
	 * @param type the new type for the actor
	 */
	public void setType(ActorType type) {
		Type = type;
	}
	
	/**
	 * Setter for the {@code Actor}'s type.
	 * It has only convenience purposes.
	 * 
	 * @param type the new type for the actor in String
	 */
	public void setType(String type) {
		Type = ActorType.valueOf(type);
	}
}
