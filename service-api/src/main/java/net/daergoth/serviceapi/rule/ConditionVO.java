package net.daergoth.serviceapi.rule;

import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

public class ConditionVO {
	
	private Long id;
	
	private ConditionTypeService type;
	
	private SensorVO sensor;
	
	private SensorDataVO value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConditionTypeService getType() {
		return type;
	}

	public void setType(ConditionTypeService type) {
		this.type = type;
	}

	public SensorVO getSensor() {
		return sensor;
	}

	public void setSensor(SensorVO sensor) {
		this.sensor = sensor;
	}

	public SensorDataVO getValue() {
		return value;
	}

	public void setValue(SensorDataVO value) {
		this.value = value;
	}
	
}
