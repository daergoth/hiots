package net.daergoth.coreapi.actor;

import java.io.Serializable;

/**
 * Represents {@code Actor} entities in the Core API. 
 * The {@code DummyActorDTO} subclass is representing non-physical actors.
 */
public class ActorDTO implements Serializable{
	
	private static final long serialVersionUID = -6461874807566815705L;

	private long Id;
	
	private String Name;
	
	private String Type;
	
	/**
	 * Getter for {@code Actor}'s ID.
	 * @return the ID of the actor
	 */
	public long getId() {
		return Id;
	}
	
	/**
	 * Getter for {@code Actor}'s name.
	 * @return the name of the actor
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Getter for {@code Actor}'s type.
	 * @return the type of the actor
	 */
	public String getType() {
		return Type;
	}
	
	/**
	 * Setter for {@code Actor}'s ID.
	 * @param id the new ID for the actor
	 */
	public void setId(long id) {
		this.Id = id;
	}

	/**
	 * Setter for {@code Actor}'s name.
	 * @param name the new name for the actor
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * Setter for {@code Actor}'s type.
	 * @param type the new type of the actor
	 */
	public void setType(String type) {
		this.Type = type;
	}

}
