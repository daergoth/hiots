package net.daergoth.coreapi.sensor;

import java.util.List;

public interface SensorDaoLocal {
	
	public List<SensorDTO> getSensors();
	
	public void addSensor(SensorDTO s);
	
	public void deleteSensor(long id);
	
	public void deleteSensors(List<Long> ids);
	
	public void updateSensor(SensorDTO s);


	
}
