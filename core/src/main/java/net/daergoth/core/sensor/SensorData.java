package net.daergoth.core.sensor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import net.daergoth.coreapi.sensor.SensorDataType;

@Embeddable
@Access(AccessType.FIELD)
public class SensorData {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SensorDataType sensorDataType;
	
	@Column(nullable = false)
	private Double sensorDataValue;

	public SensorDataType getSensorDataType() {
		return sensorDataType;
	}

	public void setSensorDataType(SensorDataType sensorDataType) {
		this.sensorDataType = sensorDataType;
	}

	public Double getSensorDataValue() {
		return sensorDataValue;
	}

	public void setSensorDataValue(Double sensorDataValue) {
		this.sensorDataValue = sensorDataValue;
	}

}
