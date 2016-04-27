package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

public class ThermostatActorVO extends ActorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8037147464615005020L;
	
	

	public ThermostatActorVO() {
		super();
		this.Type = ActorType.Thermostat;
	}

	public ThermostatActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Thermostat;
	}

	@Override
	public void setState(ActorStateVO state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(ThermostatActorStateVO.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}

}
