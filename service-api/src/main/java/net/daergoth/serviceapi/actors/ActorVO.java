package net.daergoth.serviceapi.actors;

import java.io.Serializable;

import net.daergoth.serviceapi.actors.states.ActorState;

public abstract class ActorVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183897527108648159L;
	
	protected long Id;
	
	protected String Name;
	
	protected ActorState State;
	
	protected ActorType Type;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ActorState getState() {
		return State;
	}

	public abstract void setState(ActorState state) throws InvalidActorStateTypeException;

	public ActorType getType() {
		return Type;
	}

	public void setType(ActorType type) {
		Type = type;
	}
	
	public void setType(String type) {
		Type = ActorType.valueOf(type);
	}
}
