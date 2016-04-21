package net.daergoth.serviceapi;

import net.daergoth.serviceapi.sensors.SensorVO;

public interface DataChangeListenerLocal {
	
	public void changed(SensorVO sensor);
	
	public void subscribeFor(SensorVO sensor, DataChangeHandler handler);
	
	public void unsubscribeFrom(SensorVO sensor, DataChangeHandler handler);
	
	//TODO: subscribeFor/unsunscribeFrom Actor
	
}
