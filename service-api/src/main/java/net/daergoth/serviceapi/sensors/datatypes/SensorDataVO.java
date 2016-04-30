package net.daergoth.serviceapi.sensors.datatypes;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

public abstract class SensorDataVO {
	
	protected SensorType type;
	
	public SensorDataVO() {
		
	}

	public abstract int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException;
	
	public abstract Double getData();
	
	public abstract void setData(Double d);
	
	@Override
	public abstract String toString();

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}
	
}
