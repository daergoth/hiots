package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class LampActorState implements ActorState {
	
	private boolean status;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int compareTo(ActorState other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(LampActorState.class)) {
			LampActorState o = (LampActorState) other;
			return status == o.getStatus() ? 0 : 1;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}
	
	@Override
	public String toString() {
		return status?"On":"Off";
	}
	
}
