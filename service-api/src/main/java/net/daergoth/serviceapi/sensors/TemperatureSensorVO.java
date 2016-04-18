package net.daergoth.serviceapi.sensors;

public class TemperatureSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemperatureSensorVO(long id, String name) {
		super(id, name);
		this.Type = "Temperature";
	}
}
