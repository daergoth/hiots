package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.datatypes.SensorData;

public class LightSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LightSensorVO(long id, String name) {
		super(id, name);
		this.Type = "Light";
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		//TODO
	}
	
}
