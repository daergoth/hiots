package net.daergoth.serviceapi.rule;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.states.ActorStateVO;

public class ActionVO {
	
	private Long id;
	
	private ActorVO actor;
	
	private ActorStateVO value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActorVO getActor() {
		return actor;
	}

	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	public ActorStateVO getValue() {
		return value;
	}

	public void setValue(ActorStateVO value) {
		this.value = value;
	}
	
	
	
}
