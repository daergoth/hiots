package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

public class LightDataVO extends SensorDataVO {
	
	public static String UNIT = "lm";
	
	private double lightness = 0;

	public LightDataVO(double lightness) {
		super();
		this.lightness = lightness;
		this.type = SensorType.Light;
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
		stringBuilder.append(LightDataVO.UNIT);
		return stringBuilder.toString();
	}

	@Override
	public int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(LightDataVO.class)) {
			LightDataVO o = (LightDataVO) other;
			return Double.compare(getLightness(), o.getLightness());
		} else {
			throw new InvalidSensorDataTypeException("LightData expected!");
		}
	}

	@Override
	public Double getData() {
		return lightness;
	}

	@Override
	public void setData(Double d) {
		this.lightness = d;
	}

}
