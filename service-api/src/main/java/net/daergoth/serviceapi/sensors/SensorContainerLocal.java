package net.daergoth.serviceapi.sensors;

import java.util.List;

import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

/**
 * Defines all methods, that a Sensor-provider service-bean must have.
 * Used for getting {@code Sensor}s from a {@code SensorDAO}.
 * For it's default implementation check the Service layer.
 */
public interface SensorContainerLocal {
	
	/**
	 * Retrieves all {@code Sensor}s from the database.
	 * 
	 * @return a list of sensors
	 */
	public List<SensorVO> getSensors();
	
	/**
	 * Retrieves all {@code DummySensor}s from the database.
	 * 
	 * @return a list of dummy sensors
	 */
	public List<DummySensorVO> getDummySensors();
	
	/**
	 * Adds a {@code Sensor} to the database.
	 * 
	 * @param sensor  the sensor to persist
	 */
	public void addSensor(SensorVO sensor);
	
	/**
	 * Updates an {@code Actor} in the database.
	 * 
	 * @param sensor  the sensor to update
	 */
	public void updateSensor(SensorVO sensor);
	
	/**
	 * Deletes a {@code Sensor} from the database.
	 * 
	 * @param id  the ID of the sensor to delete
	 */
	public void deleteSensor(long id);
	
	/**
	 * Deletes multiple {@code Actor}s from the database.
	 * 
	 * @param ids  the list of IDs of sensors to delete
	 */
	public void deleteSensors(List<Long> ids);
	
}
