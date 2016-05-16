package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * {@code Sensor} subclass representing a light measuring sensor in real life.
 * {@code SensorData} belonging to this type of {@code Sensor}
 * represents light intensity measured in lux.
 * 
 * @see net.daergoth.serviceapi.sensors.datatypes.LightDataVO
 */
public class LightSensorVO extends SensorVO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new {@code Sensor} measuring light intensity.
	 * @param id  the ID of the sensor
	 * @param name  the name of the sensor
	 */
	public LightSensorVO(long id, String name) {
		super(id, name);
		this.Type = SensorType.Light;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(SensorDataVO d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(LightDataVO.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("LightData expected!");
	}
	
}
