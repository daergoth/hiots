package net.daergoth.serviceapi;

import java.util.List;

import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

public interface SensorContainerLocal {
	
	public List<SensorVO> getSensors();
	
	public List<DummySensorVO> getDummySensors();
	
	public void addSensor(SensorVO s);
	
	public void updateSensor(SensorVO s);
	
	public void deleteSensor(long id);
	
	public void deleteSensors(List<Long> ids);

	public long getNewId();
	
}
