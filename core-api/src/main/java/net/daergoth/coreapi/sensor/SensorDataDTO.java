package net.daergoth.coreapi.sensor;

import java.io.Serializable;

public class SensorDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4322557470449505912L;
	
	private SensorDataType type;
	
	private Double value;

	public SensorDataType getType() {
		return type;
	}

	public void setType(SensorDataType type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}
