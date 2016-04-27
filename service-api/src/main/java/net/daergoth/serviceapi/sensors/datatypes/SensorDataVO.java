package net.daergoth.serviceapi.sensors.datatypes;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public interface SensorDataVO {
	
	public int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException;
	
	@Override
	public String toString();
	
}
