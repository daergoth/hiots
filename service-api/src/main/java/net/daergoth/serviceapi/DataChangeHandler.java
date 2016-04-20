package net.daergoth.serviceapi;

import net.daergoth.serviceapi.datatypes.SensorData;

public interface DataChangeHandler {
	
	public void onChange(SensorData newData);
	
}
