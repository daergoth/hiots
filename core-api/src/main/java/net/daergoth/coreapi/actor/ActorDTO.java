package net.daergoth.coreapi.actor;

import java.io.Serializable;

public class ActorDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6461874807566815705L;

	private long Id;
	
	private String Name;
	
	private String Type;
	
	public long getId() {
		return Id;
	}
	
	public String getName() {
		return Name;
	}

	public String getType() {
		return Type;
	}
	
	public void setId(long id) {
		this.Id = id;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public void setType(String type) {
		this.Type = type;
	}

}
