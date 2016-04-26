package net.daergoth.coreapi.actor;

import java.io.Serializable;

public class ActorStateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4150500658942319931L;
	
	private ActorStateType type;
	
	private Double value;

	public ActorStateType getType() {
		return type;
	}

	public void setType(ActorStateType type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
