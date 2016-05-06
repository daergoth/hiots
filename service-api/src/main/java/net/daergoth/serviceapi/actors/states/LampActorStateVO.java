package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class LampActorStateVO extends ActorStateVO {
	
	private boolean status;

	public LampActorStateVO() {
		super();
		this.type = ActorType.Lamp;
	}

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

	@Override
	public Double getData() {
		return status?1.0:0.0;
	}

	@Override
	public void setData(Double d) {
		this.status = d==1;
	}
	
}
