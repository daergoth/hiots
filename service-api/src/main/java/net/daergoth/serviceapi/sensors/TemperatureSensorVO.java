package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.datatypes.SensorData;

public class TemperatureSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemperatureSensorVO(long id, String name) {
		super(id, name);
		this.Type = "Temperature";
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		// TODO
	}
	
	
}
