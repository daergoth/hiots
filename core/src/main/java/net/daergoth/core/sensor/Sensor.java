package net.daergoth.core.sensor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * JPA Entity class for {@code Sensor}s.
 * Defines how a {@code Sensor} will look like in the database.
 * Instances of this entity might have a relation with a {@code DummySensorInformation},
 * which represents that the sensor is simulated. 
 *
 * @see net.daergoth.core.sensor.DummySensorInformation
 */
@Entity
@Table(name="sensors")
@NamedQueries({
	@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s") 
})
public class Sensor implements Serializable {

	private static final long serialVersionUID = -1683731640470306082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String type;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "DUMMY_ID")
	private DummySensorInformation dummyInfo;

	/**
	 * Getter for the {@code Sensor}'s additional information used for simulation.
	 * @return the sensor's simulation information entity
	 */
	public DummySensorInformation getDummyInfo() {
		return dummyInfo;
	}

	/**
	 * Setter for the {@code Sensor}'s additional information used for simulation.
	 * @param dummyInfo the new simulation information entity for the sensor
	 */
	public void setDummyInfo(DummySensorInformation dummyInfo) {
		this.dummyInfo = dummyInfo;
	}

	/**
	 * Getter for the {@code Sensor}'s ID.
	 * @return the ID of the sensor
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Sensor}'s ID.
	 * @param id the new ID for the sensor
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Sensor}'s name.
	 * @return the name of the sensor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the {@code Sensor}'s name.
	 * @param name the new name for the sensor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the {@code Sensor}'s type.
	 * @return the type of the sensor
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the {@code Sensor}'s type.
	 * @param type the new type for the sensor
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
