package net.daergoth.serviceapi;

import java.util.List;

import net.daergoth.serviceapi.sensors.SensorVO;

public interface DataChangeListenerLocal {
	
	public static final long UPDATE_INTERVAL = 1000;
	
	public void changed(SensorVO sensor);
	
	public void subscribeFor(SensorVO sensor, DataChangeHandler handler);
	
	public void subscribeFor(SensorVO sensor, List<DataChangeHandler> handlers);
	
	public void unsubscribeFrom(SensorVO sensor, DataChangeHandler handler);
	
	public void unsubscribeFrom(SensorVO sensor, List<DataChangeHandler> handlers);
	
	public void unsubscribeAllFrom(SensorVO sensor);
	
	public List<DataChangeHandler> getHandlersFor(SensorVO sensor);
	
}
