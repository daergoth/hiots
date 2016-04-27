package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;

public class TemperatureDataVO implements SensorDataVO {
	
	private double temperature = 0;
	
	public TemperatureDataVO(double d) {
		this.temperature = d;
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
	public int compareTo(SensorDataVO other) throws InvalidSensorDataTypeException {
		if (other.getClass().equals(TemperatureDataVO.class)) {
			TemperatureDataVO o = (TemperatureDataVO) other;
			return Double.compare(getTemperature(), o.getTemperature());
		} else {
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
		}
	}
	
	
	
}
