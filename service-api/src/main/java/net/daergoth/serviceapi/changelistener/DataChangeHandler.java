package net.daergoth.serviceapi.changelistener;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

public interface DataChangeHandler {
	
	public void onChange(SensorDataVO newData) throws Exception;
	
}
