package net.daergoth.coreapi.sensor;

/**
 * Represents {@code Sensor}s, which are virtual devices.
 * These are needed to isolate the simulated {@code Sensor}s from real-life ones.
 */
public class DummySensorDTO extends SensorDTO {

	private static final long serialVersionUID = -4339087405771028487L;

	private double Max;
	
	private double Min;
	
	private long Interval;

	/**
	 * Getter for random-generated data's upper limit.
	 * @return the max simulated data
	 */
	public double getMax() {
		return Max;
	}

	/**
	 * Setter for random-generated data's upper limit.
	 * @param max the new maximum of simulated data
	 */
	public void setMax(double max) {
		Max = max;
	}

	/**
	 * Getter for random-generated data's lower limit.
	 * @return the minimum simulated data
	 */
	public double getMin() {
		return Min;
	}

	/**
	 * Setter for random-generated data's lower limit.
	 * @param min the new minimum of simulated data
	 */
	public void setMin(double min) {
		Min = min;
	}

	/**
	 * Getter for data generation refresh interval in milliseconds.
	 * @return the refresh interval
	 */
	public long getInterval() {
		return Interval;
	}

	/**
	 * Setter for data generation refresh interval.
	 * @param interval the new refresh interval in milliseconds
	 */
	public void setInterval(long interval) {
		Interval = interval;
	}
	
	

}
