package net.daergoth.core.sensor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dummy_sensor_information")
public class DummySensorInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5217383445604748536L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column
	double minData;
	
	@Column
	double maxData;
	
	@Column
	long refreshInterval;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMinData() {
		return minData;
	}

	public void setMinData(double min_data) {
		minData = min_data;
	}

	public double getMaxData() {
		return maxData;
	}

	public void setMaxData(double max_data) {
		maxData = max_data;
	}

	public long getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(long refresh_interval) {
		refreshInterval = refresh_interval;
	}
	
	

}
