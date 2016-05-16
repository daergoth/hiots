package net.daergoth.serviceapi.sensors;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

/**
 * {@code Sensor} subclass representing a temperature measuring sensor in real life.
 * {@code SensorData} belonging to this type of {@code Sensor}
 * represents temperature readings measured in degrees of Celsius.
 *
 * @see net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO
 */
public class TemperatureSensorVO extends SensorVO {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@code Sensor} measuring temperature.
	 * @param id  the ID of the new sensor
	 * @param name  the name of the new sensor
	 */
	public TemperatureSensorVO(long id, String name) {
		super(id, name);
		this.Type = SensorType.Temperature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(SensorDataVO d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(TemperatureDataVO.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
	}
	
	
}
