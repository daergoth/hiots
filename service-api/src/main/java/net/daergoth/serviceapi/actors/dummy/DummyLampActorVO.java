package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;

public class DummyLampActorVO extends DummyActorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9154048241965322588L;
	
	public DummyLampActorVO() {
		super();
		this.Type = ActorType.Lamp;
	}
	
	public DummyLampActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Lamp;
	}

	@Override
	public void setState(ActorStateVO state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(LampActorStateVO.class)) {
			this.State = state;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}

}
