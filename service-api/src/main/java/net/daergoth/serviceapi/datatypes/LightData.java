package net.daergoth.serviceapi.datatypes;

import java.text.DecimalFormat;

public class LightData implements SensorData {
	
	private double lightness = 0;

	public LightData(double d) {
		this.lightness = d;
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
	
	
	
}
