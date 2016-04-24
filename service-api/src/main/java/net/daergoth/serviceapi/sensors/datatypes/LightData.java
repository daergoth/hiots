package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public class LightData implements SensorData {
	
	private enum LightType {
		LUMEN
	}
	
	private double lightness = 0;
	
	private LightType lightType = LightType.LUMEN;

	public LightData(double d) {
		this.lightness = d;
	}

	public LightData(double lightness, LightType lightType) {
		super();
		this.lightness = lightness;
		this.lightType = lightType;
	}

	public double getLightness() {
		return lightness;
	}

	public void setLightness(double lightness) {
		this.lightness = lightness;
	}

	public LightType getLightType() {
		return lightType;
	}

	public void setLightType(LightType lightType) {
		this.lightType = lightType;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(lightness));
		switch (lightType) {
		case LUMEN:
			stringBuilder.append("lm");
			break;
		default:
			stringBuilder.append("lm");
			break;
		}
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
