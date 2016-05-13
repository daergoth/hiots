package net.daergoth.core.sensor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA Entity representing simulated {@code Sensor}s' additional information,
 * Each instance of this class are in relation with a {@code Sensor} entity.
 *
 * @see net.daergoth.core.sensor.Sensor
 */
@Entity
@Table(name="dummy_sensor_information")
public class DummySensorInformation implements Serializable {

	private static final long serialVersionUID = -5217383445604748536L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private double minData;
	
	@Column
	private double maxData;
	
	@Column
	private long refreshInterval;

	/**
	 * Getter for the {@code DummySensorInformation}'s ID.
	 * @return the ID of the information entity
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code DummySensorInformation}'s ID.
	 * @param id the new ID for the information entity
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for the simulated {@code Sensor}'s lower data generation limit.
	 * @return the lower limit of the data generation for the simulated sensor
	 */
	public double getMinData() {
		return minData;
	}

	/**
	 * Setter for the simulated {@code Sensor}'s lower data generation limit.
	 * @param minData the new lower limit of the data generation for the simulated sensor
	 */
	public void setMinData(double minData) {
		this.minData = minData;
	}

	/**
	 * Getter for the simulated {@code Sensor}'s upper data generation limit.
	 * @return the upper limit of the data generation for the simulated sensor
	 */
	public double getMaxData() {
		return maxData;
	}

	/**
	 * Setter for the simulated {@code Sensor}'s upper data generation limit.
	 * @param maxData the new upper limit of the data generation for the simulated sensor
	 */
	public void setMaxData(double maxData) {
		this.maxData = maxData;
	}

	/**
	 * Getter for the simulated {@code Sensor}'s refresh rate in milliseconds.
	 * @return the refresh rate in milliseconds for the simulated sensor
	 */
	public long getRefreshInterval() {
		return refreshInterval;
	}

	/**
	 * Setter for the simulated {@code Sensor}'s refresh rate in milliseconds.
	 * @param refreshInterval the new refresh rate in milliseconds for the simulated sensor
	 */
	public void setRefreshInterval(long refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
	
	

}
