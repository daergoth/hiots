package net.daergoth.core.actor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * JPA Entity class for {@code Actor}s.
 * Defines how an {@code Actor} will look like in the database.
 */
@Entity
@Table(name="actors")
@NamedQueries({
	@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a") 
})
public class Actor implements Serializable{

	private static final long serialVersionUID = 6332682507250099159L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String type;

	/**
	 * Getter for the {@code Actor}'s ID.
	 * @return the ID of the actor
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Actor}'s ID.
	 * @param id the new ID for the actor
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Actor}'s name.
	 * @return the name of the actor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the {@code Actor}'s name.
	 * @param name the new name for the actor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the {@code Actor}'s type.
	 * @return the type of the actor
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the {@code Actor}'s  type.
	 * @param type the new type for the actor
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
