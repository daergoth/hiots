package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorState;
import net.daergoth.serviceapi.actors.states.ThermostatActorState;

public class DummyThermostatActorVO extends DummyActorVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2538590725135055427L;

	@Override
	public void setState(ActorState state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(ThermostatActorState.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}

}
