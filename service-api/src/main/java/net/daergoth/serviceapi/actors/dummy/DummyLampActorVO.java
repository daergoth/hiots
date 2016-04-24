package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorState;
import net.daergoth.serviceapi.actors.states.LampActorState;

public class DummyLampActorVO extends DummyActorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9154048241965322588L;

	@Override
	public void setState(ActorState state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(LampActorState.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}

}
