package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class LampActorStateVO implements ActorStateVO {
	
	private boolean status;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(LampActorStateVO.class)) {
			LampActorStateVO o = (LampActorStateVO) other;
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
