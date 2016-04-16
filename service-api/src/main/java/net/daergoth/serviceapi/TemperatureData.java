package net.daergoth.serviceapi;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.SensorData;

public class TemperatureData implements SensorData {
	
	private enum TemperatureType {
		CELSIUS,
		FAHRENHEIT,
		KELVIN
	}
	
	private double temperature = 0;
	
	private TemperatureType tempType = TemperatureType.CELSIUS; 
	
	public TemperatureData(double d) {
		this.temperature = d;
	}

	public TemperatureData(float temperature, TemperatureType tempType) {
		this.temperature = temperature;
		this.tempType = tempType;
	}

	public TemperatureType getTempType() {
		return tempType;
	}

	public void setTempType(TemperatureType tempType) {
		this.tempType = tempType;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(temperature));
		switch (tempType) {
		case CELSIUS:
			stringBuilder.append("°C");
			break;
		case FAHRENHEIT:
			stringBuilder.append("°F");
			break;
		case KELVIN:
			stringBuilder.append("K");
			break;
		}
		
		return stringBuilder.toString();
	}
	
	
	
}
