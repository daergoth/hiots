package net.daergoth.core.actor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="actors")
@NamedQueries({
	@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a") 
})
public class Actor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6332682507250099159L;
	
	@Id
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
