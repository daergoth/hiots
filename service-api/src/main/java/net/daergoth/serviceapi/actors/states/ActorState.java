package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public interface ActorState {
	
	public String toString();
	
	public int compareTo(ActorState other) throws InvalidActorStateTypeException;
}
