package net.daergoth.core.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.daergoth.core.sensor.Sensor;
import net.daergoth.core.sensor.SensorData;
import net.daergoth.coreapi.rule.ConditionTypeCore;

/**
 * JPA Entity class for {@code Condition}s.
 * Each instance of this class have a parent {@code Rule} entity, as well as
 * a {@code Sensor} and {@code SensorData} entity, both of them used for 
 * evaluating the {@code Condition} itself.
 * 
 * @see net.daergoth.core.rule.Rule
 * @see net.daergoth.core.sensor.Sensor
 * @see net.daergoth.core.sensor.SensorData
 */
@Entity
@Table(name = "conditions")
public class Condition implements Serializable {

	private static final long serialVersionUID = -5061363642467257220L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ConditionTypeCore type;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "sensorId", referencedColumnName="id")
	private Sensor sensor;
	
	@Embedded
	private SensorData value;

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
	public ConditionTypeCore getType() {
		return type;
	}

	/**
	 * Setter for the {@code Condition}'s type.
	 * @param type the new type for the condition
	 */
	public void setType(ConditionTypeCore type) {
		this.type = type;
	}

	/**
	 * Getter for the {@code Condition}'s  {@code Sensor} from where the measurements come from.
	 * @return the sensor of the condition
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * Setter for the {@code Condition}'s  {@code Sensor} from where the measurements come from.
	 * @param sensor the new sensor for the condition
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter for the {@code Condition}'s value, which is compared to the {@code Sensor}'s measurements.
	 * @return the value of the condition
	 */
	public SensorData getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Condition}'s value, which is compared to the {@code Sensor}'s measurements.
	 * @param value the new value for the condition
	 */
	public void setValue(SensorData value) {
		this.value = value;
	}   
	
}
