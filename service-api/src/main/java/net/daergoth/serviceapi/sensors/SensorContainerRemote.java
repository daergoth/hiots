package net.daergoth.serviceapi.sensors;

import java.util.List;

import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

public interface SensorContainerRemote {
	
	public List<SensorVO> getSensors();
	
	public List<DummySensorVO> getDummySensors();
	
	public void addSensor(SensorVO s);
	
	public void updateSensor(SensorVO s);
	
	public void deleteSensor(long id);
	
	public void deleteSensors(List<Long> ids);

	public long getNewId();
	
}
