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

@Entity
@Table(name = "conditions")
public class Condition implements Serializable {
	   
	/**
	 * 
	 */
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

	public Condition() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConditionTypeCore getType() {
		return type;
	}

	public void setType(ConditionTypeCore type) {
		this.type = type;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public SensorData getValue() {
		return value;
	}

	public void setValue(SensorData value) {
		this.value = value;
	}   
	
}
