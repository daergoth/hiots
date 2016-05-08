package net.daergoth.serviceapi;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

public interface DataChangeHandler {
	
	public void onChange(SensorDataVO newData) throws Exception;
	
}
