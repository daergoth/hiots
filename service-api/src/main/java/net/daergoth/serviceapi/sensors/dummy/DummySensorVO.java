package net.daergoth.serviceapi.sensors.dummy;

import net.daergoth.serviceapi.sensors.SensorVO;

/**
 * Base class for all types of {@code DummySensor}s.
 * Every class that represents a type of a {@code Sensor}, which is simulated,
 * must extend this class.
 * <p>
 * Also since this is a base for more specific {@code Sensor} classes,
 * you should only use it for inheriting base fields and methods.
 *
 */
public abstract class DummySensorVO extends SensorVO {

	private static final long serialVersionUID = 1L;
	
	protected double minData;
	
	protected double maxData;
	
	protected long interval;
	
	/**
	 * Constructs a new type-less {@code DummySensor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 * 
	 * @param id  the ID of the new sensor
	 * @param name  the name of the new sensor
	 * @param min  the lower limit of the sensor's data
	 * @param max  the upper limit of the sensor's data
	 * @param interval  the sensor's refresh time in milliseconds
	 */
	public DummySensorVO(long id, String name, double min, double max, long interval) {
		super(id, name);
		this.minData = min;
		this.maxData = max;
		this.interval = interval;
	}

	/**
	 * Generates new {@code SensorData} for the {@code Sensor}.
	 * Usually called by {@code DummyDataGenerator} service.
	 * 
	 * @see net.daergoth.serviceapi.sensors.DummyDataGeneratorLocal
	 */
	public abstract void generateRandomData();

	/**
	 * Getter for the {@code DummySensor}'s refresh time.
	 * @return the sensor's refresh interval in milliseconds
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * Setter for the {@code DummySensor}'s refresh time.
	 * @param interval  the new refresh time for the sensor in milliseconds
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}

	/**
	 * Getter for the {@code DummySensor}'s lower {@code SensorData} limit.
	 * @return the lower limit of the sensor's data
	 */
	public double getMinData() {
		return minData;
	}

	/**
	 * Setter for the {@code DummySensor}'s lower {@code SensorData} limit.
	 * @param minData  the new lower limit for the sensor's data.
	 */
	public void setMinData(double minData) {
		this.minData = minData;
	}

	/**
	 * Getter for the {@code DummySensor}'s upper {@code SensorData} limit.
	 * @return the upper limit of the sensor's data
	 */
	public double getMaxData() {
		return maxData;
	}

	/**
	 * Setter for the {@code DummySensor}'s upper {@code SensorData} limit.
	 * @param maxData  the new upper limit for the sensor's data
	 */
	public void setMaxData(double maxData) {
		this.maxData = maxData;
	}
	
	
}
