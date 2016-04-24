package net.daergoth.core;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sensors")
@NamedQueries({
	@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s") 
})
public class Sensor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1683731640470306082L;

	@Id
	long id;
	
	@Column
	String name;
	
	@Column
	String type;
	
	@OneToOne(cascade=CascadeType.ALL)
	DummySensorInformation dummyInfo;

	public DummySensorInformation getDummyInfo() {
		return dummyInfo;
	}

	public void setDummyInfo(DummySensorInformation dummyInfo) {
		this.dummyInfo = dummyInfo;
	}

	public Long getId() {
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
