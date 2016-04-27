package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;

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
	public void setState(ActorStateVO state) throws InvalidActorStateTypeException {
		if (state.getClass().equals(LampActorStateVO.class)) {
			LampActorStateVO s = (LampActorStateVO) state;
			this.State = s;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}

}
