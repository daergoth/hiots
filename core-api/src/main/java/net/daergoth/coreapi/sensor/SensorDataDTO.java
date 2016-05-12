package net.daergoth.coreapi.sensor;

import java.io.Serializable;

/**
 * Represents a {@code Sensor}'s measurement in the Core API.
 * 
 * @see net.daergoth.coreapi.sensor.SensorDTO
 */
public class SensorDataDTO implements Serializable {

	private static final long serialVersionUID = 4322557470449505912L;
	
	private SensorDataType type;
	
	private Double value;

	/**
	 * Getter for {@code SensorData}'s type.
	 * @return the type of the measurement
	 */
	public SensorDataType getType() {
		return type;
	}

	/**
	 * Setter for {@code ActorState}'s type.
	 * @param type the new type of the measurement
	 */
	public void setType(SensorDataType type) {
		this.type = type;
	}

	/**
	 * Getter for {@code ActorState}'s value.
	 * @return the value of the measurement
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Setter for {@code ActorState}'s value.
	 * @param value the new value of the measurement
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	
}
