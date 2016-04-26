package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public class TemperatureData implements SensorData {
	
	private double temperature = 0;
	
	public TemperatureData(double d) {
		this.temperature = d;
	}

	public TemperatureData(float temperature) {
		this.temperature = temperature;
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
		stringBuilder.append("°C");
		
		return stringBuilder.toString();
	}

	@Override
	public int compareTo(SensorData other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(TemperatureData.class)) {
			TemperatureData o = (TemperatureData) other;
			return Double.compare(getTemperature(), o.getTemperature());
		} else {
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
		}
	}
	
	
	
}
