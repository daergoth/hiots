package net.daergoth.serviceapi;

import java.io.Serializable;

public class  SensorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected long Id;
	
	protected String Name;
	
	protected SensorData data;
	
	protected String Type;
	
	public SensorVO(long id, String name) {
		Id = id;
		Name = name;
	}

	public SensorData getData() {
		return data;
	}

	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		this.Type = type;
	}
	
	
	 
	
}
