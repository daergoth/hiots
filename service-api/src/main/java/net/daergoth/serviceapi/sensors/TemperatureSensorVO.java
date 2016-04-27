package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

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
	public void setData(SensorDataVO d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(TemperatureDataVO.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
	}
	
	
}
