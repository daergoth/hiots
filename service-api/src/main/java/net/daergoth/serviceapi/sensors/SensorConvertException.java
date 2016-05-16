package net.daergoth.serviceapi.sensors;

/**
 * Exception thrown, when a problem occurs during {@code Sensor} converting. 
 *
 */
public class SensorConvertException extends Exception {

	private static final long serialVersionUID = 2290001541164039584L;

	/**
	 * Constructs a new {@code SensorConvertException}.
	 * @param msg  the message shown, when exception occurs
	 */
	public SensorConvertException(String msg) {
		super(msg);
	}
	
	

}
