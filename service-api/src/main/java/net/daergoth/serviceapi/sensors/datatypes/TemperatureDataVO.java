package net.daergoth.serviceapi.sensors.datatypes;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;

public class TemperatureDataVO extends SensorDataVO {
	
	public static String UNIT;
	
	private double temperature = 0;
	
	private String customUnit;
	
	public TemperatureDataVO(double d) {
		this.temperature = d;
		this.type = SensorType.Temperature;
		TemperatureDataVO.UNIT = "°C";
		this.customUnit = TemperatureDataVO.UNIT;
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
		stringBuilder.append(customUnit);
		
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

	public String getCustomUnit() {
		return customUnit;
	}

	public void setCustomUnit(String unit) {
		this.customUnit = unit;
	}
	
	
	
}
