package net.daergoth.serviceapi.changelistener;

import java.util.List;

/**
 * Listener service for {@code Sensor} reading changes.
 * Every {@code UPDATE_INTERVAL} milliseconds the service check for changes, 
 * and if there is change for one {@code Sensor} all {@code DataChangeHandler}s
 * related to it will be executed.
 * <p>
 * Usage example:
 * <pre>
 * ...
 * DataChangeListenerLocal changeListener;
 * 
 * SensorVO sensor = new TemperatureSensorVO(1, "Example Temperature sensor");
 * 
 * changeListener.subscribeFor(sensor.getId(), new DataChangeHandler() {
 *			
 *   public void onChange(SensorDataVO newData) {
 *     // Do something...
 *   }
 * });
 * ...
 * </pre>
 * 
 * 
 * @see net.daergoth.serviceapi.changelistener.DataChangeHandler
 */
public interface DataChangeListenerLocal {
	
	/**
	 * Time interval in milliseconds for data change checking frequency.
	 */
	public static final long UPDATE_INTERVAL = 5000;
	
	/**
	 * Subscribe a {@code DataChangeHandler} for a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  the ID of the sensor to subscribe for
	 * @param handler  the handler object to be executed on sensor's data change
	 */
	public void subscribeFor(Long sensorId, DataChangeHandler handler);
	
	/**
	 * Subscribe a list of {@code DataChangeHandler}s for a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  he ID of the sensor to subscribe for
	 * @param handlers  the list of handler objects to be executed on sensor's data change
	 */
	public void subscribeFor(Long sensorId, List<DataChangeHandler> handlers);
	
	/**
	 * Unsubscribe a {@code DataChangeHandler} from a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  the ID of the sensor to unsubscribe from
	 * @param handler  the handler object to be unsubscribed
	 */
	public void unsubscribeFrom(Long sensorId, DataChangeHandler handler);
	
	/**
	 * Unsubscribe a list of {@code DataChangeHandler}s from a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  the ID of the sensor to unsubscribe from
	 * @param handlers  the list of handler objects to be unsubscribed
	 */
	public void unsubscribeFrom(Long sensorId, List<DataChangeHandler> handlers);
	
	/**
	 * Unsubscribe all {@code DataChangeHandler}s from a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  the ID of the sensor to unsubscribe from
	 */
	public void unsubscribeAllFrom(Long sensorId);
	
	/**
	 * Get all {@code DataChangeHandler}s subscribed for a {@code Sensor}'s data change event.
	 * 
	 * @param sensorId  the ID of the sensor 
	 * @return the list of handlers
	 */
	public List<DataChangeHandler> getHandlersFor(Long sensorId);
	
}
