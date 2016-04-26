package net.daergoth.coreapi.rule;

import java.io.Serializable;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorStateDTO;

public class ActionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8278253653345639623L;
	
	private Long id;
	
	private ActorDTO actor;
	
	private ActorStateDTO value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActorDTO getActor() {
		return actor;
	}

	public void setActor(ActorDTO actor) {
		this.actor = actor;
	}

	public ActorStateDTO getValue() {
		return value;
	}

	public void setValue(ActorStateDTO value) {
		this.value = value;
	}
	
	
}
