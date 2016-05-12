package net.daergoth.coreapi.rule;

import java.io.Serializable;

import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDataDTO;

/**
 * Represents a {@code Condition} in a {@code Rule} in the Core API.
 *
 * @see net.daergoth.coreapi.rule.RuleDTO
 */
public class ConditionDTO implements Serializable {

	private static final long serialVersionUID = 8952029164294352645L;
	
	private Long id;
	
	private ConditionTypeCore conditionType;
	
	private SensorDTO sensor;
	
	private SensorDataDTO value;

	/**
	 * Getter for the {@code Condition}'s ID.
	 * @return the ID of the condition
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Condition}'s ID.
	 * @param id the new ID for the condition
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Condition}'s type.
	 * @return the type of the condition
	 */
	public ConditionTypeCore getConditionType() {
		return conditionType;
	}

	/**
	 * Setter for the {@code Condition}'s type.
	 * @param conditionType the new type for the condition
	 */
	public void setConditionType(ConditionTypeCore conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * Getter for the {@code Condition}'s {@code Sensor} from where measurements come for the condition evaluation.
	 * @return the sensor of the condition
	 */
	public SensorDTO getSensor() {
		return sensor;
	}

	/**
	 * Setter for the {@code Condition}'s {@code Sensor} from where measurements come for the condition evaluation.
	 * @param sensor the new sensor for the condition
	 */
	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter for the {@code Condition}'s value which will be compared to the {@code Sensor}'s data.
	 * @return the value of the condition
	 */
	public SensorDataDTO getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Condition}'s value which will be compared to the {@code Sensor}'s data.
	 * @param value the new value for the condition
	 */
	public void setValue(SensorDataDTO value) {
		this.value = value;
	}
	
}
