package net.daergoth.serviceapi.sensors.dummy;

import java.util.Random;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

/**
 * {@code Sensor} subclass representing a simulated temperature measuring sensor.
 * {@code SensorData} belonging to this type of {@code Sensor}
 * represents temperature readings measured in degrees of Celsius.
 * 
 * @see net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO
 */
public class DummyTemperatureSensorVO extends DummySensorVO {

	private static final long serialVersionUID = -16359446176096506L;

	private Random rand = new Random();
	
	/**
	 * Constructs a new simulated {@code Sensor} measuring temperature.
	 * @param id  the ID of the new sensor
	 * @param name  the name of the new sensor
	 * @param minData  the lower limit of the sensor's data
	 * @param maxData  the upper limit of the sensor's data
	 * @param interval  the sensor's refresh time in milliseconds
	 */
	public DummyTemperatureSensorVO(long id, String name, double minData, double maxData, long interval) {
		super(id, name, minData, maxData, interval);
		this.Type = SensorType.Temperature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TemperatureDataVO getData() {
		return (TemperatureDataVO) data;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generateRandomData() {
		
		try {
			setData(new TemperatureDataVO(rand.nextFloat()*(maxData-minData)+minData));
		} catch (InvalidSensorDataTypeException e) {
			e.printStackTrace();
		}
	}
	
}
