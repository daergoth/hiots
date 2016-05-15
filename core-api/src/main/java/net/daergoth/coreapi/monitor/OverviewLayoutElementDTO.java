package net.daergoth.coreapi.monitor;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

/**
 * Represents an {@code OverviewLayoutElement} in the Core API.
 * Each instance of this class have a parent {@code OverviewLayoutDTO}.
 * 
 * @see net.daergoth.coreapi.monitor.OverviewLayoutDTO
 */
public class OverviewLayoutElementDTO {
	
	private Long id;
	
	private String type;
	
	private ActorDTO actor;
	
	private SensorDTO sensor;
	
	private int column;
	
	private int row;

	/**
	 * Getter for the {@code OverviewLayoutElement}'s ID.
	 * @return the ID of the layout element
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s ID.
	 * @param id  the new ID for the layout element
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s type.
	 * @return the type of the layout element
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s type.
	 * @param type  the type of layout element
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s {@code Actor}.
	 * @return the actor of the layout element, can be null depends on type
	 */
	public ActorDTO getActor() {
		return actor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s {@code Actor}.
	 * @param actor  the new actor for the layout element
	 */
	public void setActor(ActorDTO actor) {
		this.actor = actor;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s {@code Sensor}.
	 * @return the sensor of the layout element, can be null depends on type
	 */
	public SensorDTO getSensor() {
		return sensor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s {@code Sensor}.
	 * @param sensor  the new sensor for the layout element
	 */
	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s column number.
	 * @return the column number of the layout element
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s column number.
	 * @param column  the new column number for the layout element
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Getter for the {@code OverviewLayoutElement}'s row number.
	 * @return the row number of the layout element
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s row number.
	 * @param row  the new row number for the layout element
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
}
