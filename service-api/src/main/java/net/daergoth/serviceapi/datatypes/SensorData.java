package net.daergoth.serviceapi.datatypes;

import net.daergoth.serviceapi.InvalidSensorDataTypeException;

public interface SensorData {
	public int compareTo(SensorData other) throws InvalidSensorDataTypeException;
}
