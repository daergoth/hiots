package net.daergoth.serviceapi;

import java.util.List;

import net.daergoth.serviceapi.sensors.SensorVO;

public interface DataChangeListenerLocal {
	
	public static final long UPDATE_INTERVAL = 5000;
	
	public void changed(SensorVO sensor);
	
	public void subscribeFor(Long sensorId, DataChangeHandler handler);
	
	public void subscribeFor(Long sensorId, List<DataChangeHandler> handlers);
	
	public void unsubscribeFrom(Long sensorId, DataChangeHandler handler);
	
	public void unsubscribeFrom(Long sensorId, List<DataChangeHandler> handlers);
	
	public void unsubscribeAllFrom(Long sensorId);
	
	public List<DataChangeHandler> getHandlersFor(Long sensorId);
	
}
