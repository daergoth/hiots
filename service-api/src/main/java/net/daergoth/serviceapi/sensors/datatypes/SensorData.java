package net.daergoth.serviceapi.sensors.datatypes;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public interface SensorData {
	
	public int compareTo(SensorData other) throws InvalidSensorDataTypeException;
	
	@Override
	public String toString();
	
}
