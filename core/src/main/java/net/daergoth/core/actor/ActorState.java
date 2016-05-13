package net.daergoth.core.actor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.daergoth.coreapi.actor.ActorStateType;

/**
 * Embeddable JPA class for representing an {@code Actor}'s state.
 */
@Embeddable
@Access(AccessType.FIELD)
public class ActorState {
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ActorStateType actorStateType;
	
	@Column(nullable = false)
	private Double actorStateValue;

	/**
	 * Getter for the {@code ActorState}'s type.
	 * @return the type of the state
	 */
	public ActorStateType getActorStateType() {
		return actorStateType;
	}

	/**
	 * Setter for the {@code ActorState}'s type.
	 * @param actorStateType the new type for the state
	 */
	public void setActorStateType(ActorStateType actorStateType) {
		this.actorStateType = actorStateType;
	}

	/**
	 * Getter for the {@code ActorState}'s value.
	 * @return the value of the state
	 */
	public Double getActorStateValue() {
		return actorStateValue;
	}

	/**
	 * Setter for the {@code ActorState}'s value.
	 * @param actorStateValue the new value for the state
	 */
	public void setActorStateValue(Double actorStateValue) {
		this.actorStateValue = actorStateValue;
	}
	
}
