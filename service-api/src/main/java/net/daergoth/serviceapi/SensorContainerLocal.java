package net.daergoth.serviceapi;

import java.util.List;

public interface SensorContainerLocal {
	
	public List<SensorVO> getSensors();
	
	public void addSensor(SensorVO s);
	
	public void deleteSensor(long id);
	
	public void deleteSensors(List<Long> ids);

	public long getNewId();
	
}
