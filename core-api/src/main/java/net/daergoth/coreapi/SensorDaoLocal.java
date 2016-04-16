package net.daergoth.coreapi;

import java.util.List;

public interface SensorDaoLocal {
	
	List<SensorDTO> getSensors();
	
	void addSensor(SensorDTO s);
	
	void deleteSensor(long id);
	
	void deleteSensors(List<Long> ids);
	
	void updateSensor(SensorDTO s);


	
}
