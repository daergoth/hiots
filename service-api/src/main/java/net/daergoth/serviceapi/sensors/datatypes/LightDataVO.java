package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

/**
 * {@code SensorData} subclass representing a {@code LightSensor}'s reading.
 * An instance of this class represents light intensity measured in lux.
 * 
 * @see net.daergoth.serviceapi.sensors.LightSensorVO
 */
public class LightDataVO extends SensorDataVO {
	
	/**
	 * Unit of the {@code LightData} in String format.
	 */
	public static String UNIT = "lx";
	
	private double lightness = 0;

	/**
	 * Constructs a new light intensity measurement.
	 * @param lightness  the value of the measurement
	 */
	public LightDataVO(double lightness) {
		super();
		this.lightness = lightness;
		this.type = SensorType.Light;
	}

	/**
	 * Getter for the {@code LightData}'s value.
	 * @return the value of the measurement
	 */
	public double getLightness() {
		return lightness;
	}

	/**
	 * Setter for the {@code LightData}'s value.
	 * @param lightness  the new value for the measurement
	 */
	public void setLightness(double lightness) {
		this.lightness = lightness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(lightness));
		stringBuilder.append(" " + LightDataVO.UNIT);
		return stringBuilder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(LightDataVO.class)) {
			LightDataVO o = (LightDataVO) other;
			return Double.compare(getLightness(), o.getLightness());
		} else {
			throw new InvalidSensorDataTypeException("LightData expected!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getData() {
		return lightness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(Double d) {
		this.lightness = d;
	}

}
