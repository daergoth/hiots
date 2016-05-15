package net.daergoth.serviceapi.rule;

import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

/**
 * Represents a {@code Condition} in the Rule service.
 * {@code Condition}s are for defining, when does a {@code Rule}
 * takes effect (i.e. if the temperature is greater than 20°C).
 * Each condition is in relation with a {@code Rule}.
 * 
 * @see net.daergoth.serviceapi.rule.RuleManagerServiceLocal
 * @see net.daergoth.serviceapi.rule.RuleVO
 */
public class ConditionVO {
	
	private Long id;
	
	private ConditionTypeService type;
	
	private SensorVO sensor;
	
	private SensorDataVO value;

	/**
	 * Getter for the {@code Condition}'s ID.
	 * @return the ID of the condition
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Condition}'s ID. 
	 * @param id  the new ID for the condition
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Condition}'s type.
	 * @return the type of the condition
	 */
	public ConditionTypeService getType() {
		return type;
	}

	/**
	 * Setter for the {@code Condition}'s type. 
	 * @param type  the new type for the condition
	 */
	public void setType(ConditionTypeService type) {
		this.type = type;
	}

	/**
	 * Getter for the {@code Condition}'s {@code Sensor}, from where readings come from to evaluation.
	 * @return the sensor of the condition
	 */
	public SensorVO getSensor() {
		return sensor;
	}

	/**
	 * Setter for the {@code Condition}'s {@code Sensor}, from where readings come from to evaluation.
	 * @param sensor  the sensor for the condition
	 */
	public void setSensor(SensorVO sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter for the {@code Condition}'s {@code SensorData}, which is the limit value in the condition.
	 * @return the value of the condition
	 */
	public SensorDataVO getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Condition}'s {@code SensorData}, which is the limit value in the condition.
	 * @param value  the new value for the condition
	 */
	public void setValue(SensorDataVO value) {
		this.value = value;
	}
	
}
