package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

public class TemperatureDataVO extends SensorDataVO {
	
	public static String UNIT = "°C";
	
	private double temperature = 0;
	
	public TemperatureDataVO(double d) {
		this.temperature = d;
		this.type = SensorType.Temperature;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new DecimalFormat("###.##").format(temperature));
		stringBuilder.append(TemperatureDataVO.UNIT);
		
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

	@Override
	public Double getData() {
		return temperature;
	}

	@Override
	public void setData(Double d) {
		this.temperature = d;
	}
	
}
