package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

/**
 * {@code ActorState} subclass representing {@code LampActor}'s state.
 * This class has two possible states:
 * <ul>
 * <li> On  - represented by true (or 1)
 * <li> Off - represented by false (or 0)
 * </ul>
 * 
 * @see net.daergoth.serviceapi.actors.LampActorVO
 */
public class LampActorStateVO extends ActorStateVO {
	
	private boolean status;

	/**
	 * Constructs a new {@code LampActorState}.
	 */
	public LampActorStateVO() {
		super();
		this.type = ActorType.Lamp;
	}

	/**
	 * Getter for the {@code LampActor}'s status, that this {@code ActorState} belongs to.
	 * 
	 * @return the status of the related actor
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Setter for the {@code LampActor}'s status, that this {@code ActorState} belongs to.
	 * 
	 * @param status  the new status of the related actor
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return 0 if the two states are the same, 1 if they are different
	 */
	@Override
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(LampActorStateVO.class)) {
			LampActorStateVO o = (LampActorStateVO) other;
			return status == o.getStatus() ? 0 : 1;
		} else {
			throw new InvalidActorStateTypeException("LampActorState expected!");
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return 'On' if the status is true, 'Off' if the status is false
	 */
	@Override
	public String toString() {
		return status?"On":"Off";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return 1 if the Lamp is On, 0 otherwise
	 */
	@Override
	public Double getData() {
		return status?1.0:0.0;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param d  the new data for the state (1 for On, 0 for Off) 
	 */
	@Override
	public void setData(Double d) {
		this.status = d==1;
	}
	
}
