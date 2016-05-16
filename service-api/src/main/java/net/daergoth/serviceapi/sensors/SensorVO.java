package net.daergoth.serviceapi.sensors;

import java.io.Serializable;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * Base class for all types of {@code Sensor}s.
 * Every class that represents a type of a {@code Sensor} must extend this class.
 * <p>
 * Please note that {@code DummySensorVO} is also a subclass of this class,
 * and is used for {@code Sensor} simulation.
 * <p>
 * Also since this is a base for more specific {@code Sensor} classes,
 * you should only use it for inheriting base fields and methods.
 * 
 * @see net.daergoth.serviceapi.sensors.dummy.DummySensorVO
 */
public abstract class SensorVO implements Serializable{
	
	private static final long serialVersionUID = -1655147522520293280L;
	
	protected long Id;
	
	protected String Name;
	
	protected SensorDataVO data;
	
	protected SensorType Type;
	
	/**
	 * Constructs a new type-less {@code Sensor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 * @param id  the ID of the new sensor
	 * @param name  the name of the new sensor
	 */
	public SensorVO(long id, String name) {
		Id = id;
		Name = name;
	}

	/**
	 * Getter for the {@code Sensor}'s current reading.
	 * 
	 * @return the current reading of the sensor
	 */
	public SensorDataVO getData() {
		return data;
	}
	
	/**
	 * Setter for the {@code Sensor}'s current reading.
	 * The given {@code SensorData} should have the same {@code SensorType} as the {@code Sensor}.
	 * 
	 * @param d  the new reading for the sensor
	 * @throws InvalidSensorDataTypeException if the {@code SensorData} given has wrong {@code SensorType}
	 */
	public abstract void setData(SensorDataVO d) throws InvalidSensorDataTypeException;

	/**
	 * Getter for the {@code Sensor}'s ID.
	 * @return the ID of the sensor
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * Setter for the {@code Sensor}'s ID. 
	 * @param id  the new ID for the sensor
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * Getter for the {@code Sensor}'s name.
	 * @return the name of the sensor
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Setter for the {@code Sensor}'s name. 
	 * @param name  the new name for the sensor
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Getter for the {@code Sensor}'s type.
	 * @return the type of the sensor
	 */
	public SensorType getType() {
		return Type;
	}

	/**
	 * Setter for the {@code Sensor}'s type. 
	 * @param type  the new type for the sensor
	 */
	public void setType(SensorType type) {
		this.Type = type;
	}
	
	/**
	 * Setter for the {@code Sensor}'s type. 
	 * @param type  the new type for the sensor in String format
	 */
	public void setType(String type) {
		SensorType sType = SensorType.valueOf(type);
		this.Type = sType;
	}
	 
	
}
