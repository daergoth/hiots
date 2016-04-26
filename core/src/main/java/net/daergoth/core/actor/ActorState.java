package net.daergoth.core.actor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.daergoth.coreapi.actor.ActorStateType;

@Embeddable
@Access(AccessType.FIELD)
public class ActorState {
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ActorStateType actorStateType;
	
	@Column(nullable = false)
	private Double actorStateValue;

	public ActorStateType getActorStateType() {
		return actorStateType;
	}

	public void setActorStateType(ActorStateType actorStateType) {
		this.actorStateType = actorStateType;
	}

	public Double getActorStateValue() {
		return actorStateValue;
	}

	public void setActorStateValue(Double actorStateValue) {
		this.actorStateValue = actorStateValue;
	}
	
}
