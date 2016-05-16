package net.daergoth.serviceapi.sensors;

/**
 * Exception thrown, when compare two different type of {@code SensorData} or
 * a {@code Sensor}'s {@code SensorData} doesn't match it's type.
 *
 * @see net.daergoth.serviceapi.sensors.datatypes.SensorDataVO
 */
public class InvalidSensorDataTypeException extends Exception {

	private static final long serialVersionUID = 5568729182464943791L;

	/**
	 * Constructs a new {@code InvalidSensorDataTypeException}.
	 * @param message  the message shown, when the exception occurs
	 */
	public InvalidSensorDataTypeException(String message) {
		super(message);
	}

}
