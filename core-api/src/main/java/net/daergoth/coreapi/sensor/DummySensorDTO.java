package net.daergoth.coreapi.sensor;

public class DummySensorDTO extends SensorDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339087405771028487L;

	double Max;
	
	double Min;
	
	long Interval;

	public double getMax() {
		return Max;
	}

	public void setMax(double max) {
		Max = max;
	}

	public double getMin() {
		return Min;
	}

	public void setMin(double min) {
		Min = min;
	}

	public long getInterval() {
		return Interval;
	}

	public void setInterval(long interval) {
		Interval = interval;
	}
	
	

}
