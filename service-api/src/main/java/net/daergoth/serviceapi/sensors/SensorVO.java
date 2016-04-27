package net.daergoth.serviceapi.sensors;

import java.io.Serializable;

import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

public abstract class  SensorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1655147522520293280L;
	
	protected long Id;
	
	protected String Name;
	
	protected SensorDataVO data;
	
	protected SensorType Type;
	
	public SensorVO(long id, String name) {
		Id = id;
		Name = name;
	}

	public SensorDataVO getData() {
		return data;
	}
	
	public abstract void setData(SensorDataVO d) throws InvalidSensorDataTypeException;

	public Long getId() {
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

	public SensorType getType() {
		return Type;
	}

	public void setType(SensorType type) {
		this.Type = type;
	}
	
	public void setType(String type) {
		SensorType sType = SensorType.valueOf(type);
		this.Type = sType;
	}
	 
	
}
