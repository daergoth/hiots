package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;

/**
 * {@code DummyActor} subclass representing a simulated Lamp.
 * {@code ActorState}s belonging to this type of {@code Actor} have
 * two possible states:
 * <ul>
 * <li> On
 * <li> Off
 * </ul>
 * 
 * @see net.daergoth.serviceapi.actors.states.LampActorStateVO
 */
public class DummyLampActorVO extends DummyActorVO {

	private static final long serialVersionUID = -9154048241965322588L;
	
	/**
	 * Constructs a new simulated {@code LampActor}.
	 */
	public DummyLampActorVO() {
		super();
		this.Type = ActorType.Lamp;
	}
	
	/**
	 * Constructs a new simulated {@code LampActor}.
	 * 
	 * @param id  the ID of the new actor
	 * @param name  the name of the new actor
	 */
	public DummyLampActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Lamp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setState(ActorStateVO state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(LampActorStateVO.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}

}
