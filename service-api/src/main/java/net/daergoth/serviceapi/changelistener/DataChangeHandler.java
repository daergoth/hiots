package net.daergoth.serviceapi.changelistener;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * Represents a command container object for the Sensor listener service, 
 * that is executed when the related {@code Sensor}'s data changes.
 * For more information check out {@link DataChangeListenerLocal}.
 *
 * @see net.daergoth.serviceapi.changelistener.DataChangeListenerLocal
 */
public interface DataChangeHandler {
	
	/**
	 * Called when the listener detects change in the related {@code Sensor}'s data
	 * 
	 * @param newData  the new sensor reading 
	 */
	public void onChange(SensorDataVO newData);
	
}
