package net.daergoth.core.monitor;

import java.io.Serializable;
import javax.persistence.*;
import net.daergoth.core.actor.Actor;
import net.daergoth.core.sensor.Sensor;


/**
 * JPA Entity representing an Overview layout element.
 * Each entity of this class have a parent {@code OverviewLayout} entity.
 *
 * @see net.daergoth.core.monitor.OverviewLayout
 */
@Entity
@Table(name = "overview_layout_elements")
public class OverviewLayoutElement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String type;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "actorId", referencedColumnName="id", nullable = true)
	private Actor actor;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "sensorId", referencedColumnName="id", nullable = true)
	private Sensor sensor;
	
	private int columnCode;
	
	private int rowCode;
	
	/**
	 * Getter for the {@code OverviewLayoutElement}'s ID.
	 * @return the id of the layout element
	 */
	public Long getId() {
		return this.id;
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
		return this.type;
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
	public Actor getActor() {
		return this.actor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s {@code Actor}.
	 * @param actor  the new actor for the layout element
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}  
	
	/**
	 * Getter for the {@code OverviewLayoutElement}'s {@code Sensor}.
	 * @return the sensor of the layout element, can be null depends on type
	 */
	public Sensor getSensor() {
		return this.sensor;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s {@code Sensor}.
	 * @param sensor  the new sensor for the layout element
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}  
	
	/**
	 * Getter for the {@code OverviewLayoutElement}'s column number.
	 * @return the column number of the layout element
	 */
	public int getColumn() {
		return this.columnCode;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s column number.
	 * @param column  the new column number for the layout element
	 */
	public void setColumn(int column) {
		this.columnCode = column;
	}  
	
	/**
	 * Getter for the {@code OverviewLayoutElement}'s row number.
	 * @return the row number of the layout element
	 */
	public int getRow() {
		return this.rowCode;
	}

	/**
	 * Setter for the {@code OverviewLayoutElement}'s row number.
	 * @param row  the new row number for the layout element
	 */
	public void setRow(int row) {
		this.rowCode = row;
	}
   
}
