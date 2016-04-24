package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.sensors.datatypes.LightData;
import net.daergoth.serviceapi.sensors.datatypes.SensorData;

public class LightSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LightSensorVO(long id, String name) {
		super(id, name);
		this.Type = SensorType.Light;
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(LightData.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("LightData expected!");
	}
	
}
