package net.daergoth.serviceapi.actors;

import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;

/**
 * {@code Actor} subclass representing a lamp in real life.
 * {@code ActorState}s belonging to this type of {@code Actor} have
 * two possible states:
 * <ul>
 * <li> On
 * <li> Off
 * </ul>
 * @see net.daergoth.serviceapi.actors.states.LampActorStateVO
 */
public class LampActorVO extends ActorVO {

	private static final long serialVersionUID = 7711374921543297393L;

	/**
	 * Constructs a new {@code LampActor}.
	 */
	public LampActorVO() {
		super();
		this.Type = ActorType.Lamp;
	}

	/**
	 * Constructs a new {@code LampActor}.
	 * 
	 * @param id the ID of the new actor
	 * @param name the name of the new actor
	 */
	public LampActorVO(long id, String name) {
		super(id, name);
		this.Type = ActorType.Lamp;
	}

	/**
	 * {@inheritDoc}
	 */
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
