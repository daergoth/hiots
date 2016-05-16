package net.daergoth.serviceapi.sensors.datatypes;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

/**
 * Base class for all types of {@code SensorData}s.
 * Every class that represents a {@code Sensor}'s reading
 * must extend this class.
 * <p>
 * Also since this is a base for more specific {@code SensorData} classes,
 * you should only use it for inheriting base fields and methods.
 */
public abstract class SensorDataVO {
	
	protected SensorType type;

	/**
	 * Compares another instance of the same type of {@code ActorState} to this.
	 * The comparison results differ between actual {@code ActorState} types.
	 * 
	 * @param other  the measurement to compare to
	 * @return different between actual {@code SensorData} types
	 * @throws InvalidSensorDataTypeException if the two measurements have different types
	 */
	public abstract int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException;
	
	/**
	 * Getter for the {@code SensorData} value.
	 * Meaning of this value is different between actual {@code SensorData} types.
	 * 
	 * @return  the value of the measurement
	 */
	public abstract Double getData();
	
	/**
	 * Setter for the {@code SensorData} value.
	 * Meaning of this value is different between actual {@code SensorData} types.
	 * 
	 * @param d  the new value for the measurement
	 */
	public abstract void setData(Double d);
	
	/**
	 * Gets the value of the {@code SensorData} in human-readable format.
	 * 
	 * @return the reading in human-readable String format
	 */
	@Override
	public abstract String toString();

	/**
	 * Getter for the {@code SensorData}'s type. 
	 * @return the type of the measurement
	 */
	public SensorType getType() {
		return type;
	}

	/**
	 * Setter for the {@code SensorData}'s type.
	 * @param type  the new type for the measurement
	 */
	public void setType(SensorType type) {
		this.type = type;
	}
	
}
