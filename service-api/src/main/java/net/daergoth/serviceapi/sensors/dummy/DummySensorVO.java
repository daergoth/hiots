package net.daergoth.serviceapi.sensors.dummy;

import net.daergoth.serviceapi.sensors.SensorVO;

public abstract class DummySensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected double minData;
	protected double maxData;
	protected long interval;
	
	public DummySensorVO(long id, String name, double min, double max, long interval) {
		super(id, name);
		this.minData = min;
		this.maxData = max;
		this.interval = interval;
	}

	public abstract void generateRandomData();

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public double getMinData() {
		return minData;
	}

	public void setMinData(double minData) {
		this.minData = minData;
	}

	public double getMaxData() {
		return maxData;
	}

	public void setMaxData(double maxData) {
		this.maxData = maxData;
	}
	
	
}
