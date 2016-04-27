package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public interface ActorStateVO {
	
	public String toString();
	
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException;
}
