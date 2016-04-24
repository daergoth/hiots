package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.sensors.datatypes.SensorData;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureData;

public class TemperatureSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemperatureSensorVO(long id, String name) {
		super(id, name);
		this.Type = SensorType.Temperature;
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(TemperatureData.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
	}
	
	
}
