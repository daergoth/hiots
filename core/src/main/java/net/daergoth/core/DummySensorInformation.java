package net.daergoth.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DummySensorInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	long Sensor_id;
	
	@Column
	double Min_data;
	
	@Column
	double Max_data;
	
	@Column
	long Refresh_interval;

	public long getSensor_id() {
		return Sensor_id;
	}

	public void setSensor_id(long sensor_id) {
		Sensor_id = sensor_id;
	}

	public double getMin_data() {
		return Min_data;
	}

	public void setMin_data(double min_data) {
		Min_data = min_data;
	}

	public double getMax_data() {
		return Max_data;
	}

	public void setMax_data(double max_data) {
		Max_data = max_data;
	}

	public long getRefresh_interval() {
		return Refresh_interval;
	}

	public void setRefresh_interval(long refresh_interval) {
		Refresh_interval = refresh_interval;
	}
	
	

}
