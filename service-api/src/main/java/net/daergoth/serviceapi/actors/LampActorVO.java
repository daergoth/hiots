package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorState;
import net.daergoth.serviceapi.actors.states.LampActorState;

public class LampActorVO extends ActorVO {

	public LampActorVO() {
		super();
		this.Type = ActorType.Lamp;
	}

	public LampActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Lamp;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7711374921543297393L;

	@Override
	public void setState(ActorState state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(LampActorState.class)) {
			LampActorState s = (LampActorState) state;
			this.State = s;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}

}
