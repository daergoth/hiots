package net.daergoth.serviceapi.sensors.dummy;

import java.util.Random;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * {@code DummySensor} subclass representing a simulated light measuring sensor.
 * {@code SensorData} belonging to this type of {@code Sensor}
 * represents light intensity measured in lux.
 *
 * @see net.daergoth.serviceapi.sensors.datatypes.LightDataVO
 */
public class DummyLightSensorVO extends DummySensorVO {

	private static final long serialVersionUID = 1L;
	
	private Random rand = new Random();
	
	/**
	 * Constructs a new simulated {@code Sensor}, measuring light intensity.
	 * @param id  the ID of the new sensor
	 * @param name  the name of the new sensor
	 * @param min  the lower limit of the sensor's data
	 * @param max  the upper limit of the sensor's data
	 * @param interval  the sensor's refresh time in milliseconds
	 */
	public DummyLightSensorVO(long id, String name, double min, double max, long interval) {
		super(id, name, min, max, interval);
		this.Type = SensorType.Light;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generateRandomData() {
		try {
			setData(new LightDataVO(rand.nextFloat()*(maxData-minData)+minData));
		} catch (InvalidSensorDataTypeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LightDataVO getData() {
		return (LightDataVO) data;
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
