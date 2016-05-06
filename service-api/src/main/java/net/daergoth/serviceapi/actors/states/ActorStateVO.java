package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public abstract class ActorStateVO {
	
	protected ActorType type;
	
	public abstract String toString();
	
	public abstract int compareTo(ActorStateVO other) throws InvalidActorStateTypeException;
	
	public abstract Double getData();
	
	public abstract void setData(Double d);

	public ActorType getType() {
		return type;
	}

	public void setType(ActorType type) {
		this.type = type;
	}
}
