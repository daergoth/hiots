package net.daergoth.coreapi.sensor;

import java.util.List;

/**
 * Provides access to the database for obtaining {@code Sensor} objects.
 * 
 * @see net.daergoth.coreapi.sensor.SensorDataDTO
 */
public interface SensorDaoLocal {
	
	/**
	 * Obtains all {@code Sensor}s from the database.
	 * @return the list of sensors
	 */
	public List<SensorDTO> getSensors();
	
	/**
	 * Persists a {@code Sensor} by writing it into the database.
	 * @param sensor the sensor to persist
	 */
	public void addSensor(SensorDTO sensor);
	
	/**
	 * Deletes the {@code Sensor} with specified ID from the database.
	 * @param id the ID of the sensor that will be deleted
	 */
	public void deleteSensor(long id);
	
	/**
	 * Deletes multiple {@code Sensor}s from the database.
	 * @param ids the list of IDs of sensor that will be deleted
	 */
	public void deleteSensors(List<Long> ids);
	
	/**
	 * Updates a {@code Sensor}'s data in the database.
	 * @param sensor the sensor to update
	 */
	public void updateSensor(SensorDTO sensor);
	
}
