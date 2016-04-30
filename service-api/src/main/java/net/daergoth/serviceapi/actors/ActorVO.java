package net.daergoth.serviceapi.actors;

import java.io.Serializable;

import net.daergoth.serviceapi.actors.states.ActorStateVO;

public abstract class ActorVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1183897527108648159L;
	
	protected long Id;
	
	protected String Name;
	
	protected ActorStateVO State;
	
	protected ActorType Type;
	
	public ActorVO() {
		
	}

	public ActorVO(long id, String name) {
		this.Id = id;
		this.Name = name;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ActorStateVO getState() {
		return State;
	}

	public abstract void setState(ActorStateVO state) throws InvalidActorStateTypeException;

	public ActorType getType() {
		return Type;
	}

	public void setType(ActorType type) {
		Type = type;
	}
	
	public void setType(String type) {
		Type = ActorType.valueOf(type);
	}
}
