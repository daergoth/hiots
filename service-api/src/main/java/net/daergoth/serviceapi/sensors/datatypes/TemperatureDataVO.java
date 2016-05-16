package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

/**
 * {@code SensorData} subclass representing a {@code TemperatureSensor}'s reading.
 * An instance of this class represents temperature measured in degrees of Celsius.
 *
 * @see net.daergoth.serviceapi.sensors.TemperatureSensorVO
 */
public class TemperatureDataVO extends SensorDataVO {
	
	/**
	 * Unit of the {@code TemperatureData} in String format.
	 */
	public static String UNIT = "°C";
	
	private double temperature = 0;
	
	/**
	 * Constructs a new temperature measurement.
	 * @param d  the value of the measurement
	 */
	public TemperatureDataVO(double d) {
		this.temperature = d;
		this.type = SensorType.Temperature;
	}

	/**
	 * Getter for the {@code TemperatureData}'s value.
	 * @return the value of the measurement
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Setter for the {@code TemperatureData}'s value.
	 * @param temperature  the new value for the measurement
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(temperature));
		stringBuilder.append(TemperatureDataVO.UNIT);
		
		return stringBuilder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(TemperatureDataVO.class)) {
			TemperatureDataVO o = (TemperatureDataVO) other;
			return Double.compare(getTemperature(), o.getTemperature());
		} else {
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getData() {
		return temperature;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(Double d) {
		this.temperature = d;
	}
	
}
