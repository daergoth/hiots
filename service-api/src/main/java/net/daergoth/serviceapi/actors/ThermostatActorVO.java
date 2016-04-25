package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorState;
import net.daergoth.serviceapi.actors.states.ThermostatActorState;

public class ThermostatActorVO extends ActorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8037147464615005020L;
	
	

	public ThermostatActorVO() {
		super();
		this.Type = ActorType.Thermostat;
	}



	@Override
	public void setState(ActorState state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(ThermostatActorState.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}

}
