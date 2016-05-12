package net.daergoth.coreapi.sensor;

import java.io.Serializable;

/**
 * Represents {@code Sensor} entities in the Core API.
 * The {@code DummySensorDTO} subclass is representing non-physical sensors.
 */
public class SensorDTO implements Serializable{

	private static final long serialVersionUID = -2105925091056611175L;

	private long Id;
	
	private String Name;
	
	private String Type;

	/**
	 * Getter for {@code Sensor}'s ID.
	 * @return the ID of the sensor
	 */
	public long getId() {
		return Id;
	}

	/**
	 * Setter for {@code Sensor}'s ID.
	 * @param id the new ID for the sensor
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * Getter for {@code Sensor}'s name.
	 * @return the name of the sensor
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Setter for {@code Sensor}'s name.
	 * @param name the new name of the sensor
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Getter for {@code Sensor}'s type.
	 * @return the type of the sensor
	 */
	public String getType() {
		return Type;
	}

	/**
	 * Setter for {@code Sensor}'s type.
	 * @param type the new type of the sensor
	 */
	public void setType(String type) {
		Type = type;
	}
	
}
