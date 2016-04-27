package net.daergoth.coreapi.rule;

import java.io.Serializable;

import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDataDTO;

public class ConditionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8952029164294352645L;
	
	private Long id;
	
	private ConditionTypeCore conditionType;
	
	private SensorDTO sensor;
	
	private SensorDataDTO value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConditionTypeCore getConditionType() {
		return conditionType;
	}

	public void setConditionType(ConditionTypeCore conditionType) {
		this.conditionType = conditionType;
	}

	public SensorDTO getSensor() {
		return sensor;
	}

	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

	public SensorDataDTO getValue() {
		return value;
	}

	public void setValue(SensorDataDTO value) {
		this.value = value;
	}
	
}
