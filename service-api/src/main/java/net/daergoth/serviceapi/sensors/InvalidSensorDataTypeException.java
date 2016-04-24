package net.daergoth.serviceapi.sensors;

public class InvalidSensorDataTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5568729182464943791L;

	public InvalidSensorDataTypeException() {
		super();
	}

	public InvalidSensorDataTypeException(String message) {
		super(message);
	}

}
