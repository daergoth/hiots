package net.daergoth.serviceapi.sensors;

public class LightSensorVO extends SensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LightSensorVO(long id, String name) {
		super(id, name);
		this.Type = "Light";
	}
	
}
