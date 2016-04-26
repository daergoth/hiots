package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public class LightData implements SensorData {
	
	private double lightness = 0;

	public LightData(double lightness) {
		super();
		this.lightness = lightness;
	}

	public double getLightness() {
		return lightness;
	}

	public void setLightness(double lightness) {
		this.lightness = lightness;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(lightness));
		stringBuilder.append("lm");
		return stringBuilder.toString();
	}

	@Override
	public int compareTo(SensorData other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(LightData.class)) {
			LightData o = (LightData) other;
			return Double.compare(getLightness(), o.getLightness());
		} else {
			throw new InvalidSensorDataTypeException("LightData expected!");
		}
	}
	
	
	
}
