package net.daergoth.serviceapi.monitor;

import java.io.Serializable;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public class OverviewLayoutElementVO implements Serializable {

	private static final long serialVersionUID = 7271808965913100637L;

	private Long id;
	
	private OverviewLayoutElementType type;
	
	private ActorVO actor;
	
	private SensorVO sensor;
	
	private int column;
	
	private int row;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OverviewLayoutElementType getType() {
		return type;
	}

	public void setType(OverviewLayoutElementType type) {
		this.type = type;
	}

	public ActorVO getActor() {
		return actor;
	}

	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	public SensorVO getSensor() {
		return sensor;
	}

	public void setSensor(SensorVO sensor) {
		this.sensor = sensor;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
}
