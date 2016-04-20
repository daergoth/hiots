package net.daergoth.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="dummy_sensor_information")
@NamedQueries({
	@NamedQuery(name="DummySensorInformation.findBySensorId", query="SELECT dsi FROM DummySensorInformation dsi WHERE dsi.sensorId = :sensorId")
})
public class DummySensorInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5217383445604748536L;	
	
	@Id
	long sensorId;
	
	@Column
	double minData;
	
	@Column
	double maxData;
	
	@Column
	long refreshInterval;

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(long sensor_id) {
		sensorId = sensor_id;
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
