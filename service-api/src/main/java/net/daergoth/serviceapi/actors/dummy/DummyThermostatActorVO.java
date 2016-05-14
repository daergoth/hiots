package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;

/**
 * {@code DummyActor} subclass representing a simulated Thermostat.
 * {@code ActorState}s, belonging to this type of {@code Actor}, are
 * representing a target temperature, which the thermostat is set to.
 * 
 * @see net.daergoth.serviceapi.actors.states.ThermostatActorStateVO
 */
public class DummyThermostatActorVO extends DummyActorVO{

	private static final long serialVersionUID = 2538590725135055427L;
	
	/**
	 * Constructs a new simulated {@code ThermostatActor}.
	 */
	public DummyThermostatActorVO() {
		super();
		this.Type = ActorType.Thermostat;
	}

	/**
	 * Constructs a new simulated {@code ThermostatActor}.
	 * 
	 * @param id  the ID of the new actor
	 * @param name  the name of the new actor
	 */
	public DummyThermostatActorVO(long id, String name) {
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
