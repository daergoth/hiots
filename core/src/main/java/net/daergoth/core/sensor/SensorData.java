package net.daergoth.core.sensor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.daergoth.coreapi.sensor.SensorDataType;

/**
 * Embeddable JPA class for representing a {@code Sensor}'s measurement data.
 */
@Embeddable
@Access(AccessType.FIELD)
public class SensorData {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SensorDataType sensorDataType;
	
	@Column(nullable = false)
	private Double sensorDataValue;

	/**
	 * Getter for the {@code Sensor} measurement's type.
	 * @return the type of the measurement
	 */
	public SensorDataType getSensorDataType() {
		return sensorDataType;
	}

	/**
	 * Setter for the {@code Sensor} measurement's type.
	 * @param sensorDataType the new type for the measurement
	 */
	public void setSensorDataType(SensorDataType sensorDataType) {
		this.sensorDataType = sensorDataType;
	}

	/**
	 * Getter for the {@code Sensor} measurement's value.
	 * @return the value of the measurement
	 */
	public Double getSensorDataValue() {
		return sensorDataValue;
	}

	/**
	 * Setter for the {@code Sensor} measurement's value.
	 * @param sensorDataValue the new value for the measurement
	 */
	public void setSensorDataValue(Double sensorDataValue) {
		this.sensorDataValue = sensorDataValue;
	}

}
