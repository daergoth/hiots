package net.daergoth.serviceapi.monitor;

import java.io.Serializable;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;

/**
 * Represents a layout element on the Overview page.
 * Each instance of this class has a parent {@code OverviewLayoutVO}.
 * 
 * @see net.daergoth.serviceapi.monitor.OverviewLayoutVO
 */
public class OverviewLayoutElementVO implements Serializable {

	private static final long serialVersionUID = 7271808965913100637L;

	private Long id;
	
	private OverviewLayoutElementType type;
	
	private ActorVO actor;
	
	private SensorVO sensor;
	
	private int column;
	
	private int row;

	/**
	 * Getter for the {@code OverviewLayoutElement}'s ID.
	 * @return the ID of the element
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s ID.
	 * @param id  the new ID for the element
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s type.
	 * @return the type of the element
	 */
	public OverviewLayoutElementType getType() {
		return type;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s type.
	 * @param type  the new type for the element
	 */
	public void setType(OverviewLayoutElementType type) {
		this.type = type;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s displaying {@code Actor}.
	 * @return the actor displayed by the element, can be null depends on type
	 */
	public ActorVO getActor() {
		return actor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s displaying {@code Actor}.
	 * @param actor  the new actor for the element to display
	 */
	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s displaying {@code Sensor}.
	 * @return the sensor displayed by the element, can be null depends on type
	 */
	public SensorVO getSensor() {
		return sensor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s displaying {@code Sensor}.
	 * @param sensor  the new sensor for the element to display
	 */
	public void setSensor(SensorVO sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s column number.
	 * @return the column number of the element
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s column number.
	 * @param column  the new column number for the element
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s row number.
	 * @return the row number of the element
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s row number.
	 * @param row  the new row for the element
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	
}
