package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

/**
 * {@code Actor} subclass representing a Thermostat in real life.
 * {@code ActorState}s, belonging to this type of {@code Actor}, are
 * representing a target temperature, which the thermostat is set to.
 * 
 * @see net.daergoth.serviceapi.actors.states.ThermostatActorStateVO
 */
public class ThermostatActorVO extends ActorVO {

	private static final long serialVersionUID = -8037147464615005020L;

	/**
	 * Constructs a new {@code ThermostatActor}.
	 */
	public ThermostatActorVO() {
		super();
		this.Type = ActorType.Thermostat;
	}

	/**
	 * Constructs a new {@code ThermostatActor}.
	 * 
	 * @param id  the ID of the new actor
	 * @param name  the name of the new actor
	 */
	public ThermostatActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Thermostat;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setState(ActorStateVO state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(ThermostatActorStateVO.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}

}
