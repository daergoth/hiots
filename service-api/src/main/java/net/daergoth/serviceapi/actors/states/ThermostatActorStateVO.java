package net.daergoth.serviceapi.actors.states;

import java.text.DecimalFormat;

import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

/**
 * {@code ActorState} subclass representing {@code LampActor}'s state.
 * This class represent a {@code ThermostatActor}'s target temperature.
 * 
 * @see net.daergoth.serviceapi.actors.ThermostatActorVO
 */
public class ThermostatActorStateVO extends ActorStateVO {
	
	private double targetTemperature;
	
	/**
	 * Constructs a new {@code ThermostatActorState}.
	 */
	public ThermostatActorStateVO() {
		super();
		this.type = ActorType.Thermostat;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return 0 if both values are equal, value greater than 0 if this is greater than {@code other}, value less than 0 if this is less than {@code other} 
	 */
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(ThermostatActorStateVO.class)) {
			ThermostatActorStateVO o = (ThermostatActorStateVO) other;
			return Double.compare(targetTemperature, o.getTargetTemperature());
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return target temperature of the related actor in 'XXX.XX°C' format.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(new DecimalFormat("###.##").format(targetTemperature));
		sb.append("°C");
		return sb.toString();
	}

	/**
	 * Getter for the {@code ThermostatActor}'s target temperature, that this {@code ActorState} belongs to.
	 * 
	 * @return the target temperature of the related actor
	 */
	public double getTargetTemperature() {
		return targetTemperature;
	}

	/**
	 * Setter for the {@code ThermostatActor}'s target temperature, that this {@code ActorState} belongs to.
	 * 
	 * @param targetTemperature  the new target temperature of the related actor
	 */
	public void setTargetTemperature(double targetTemperature) {
		this.targetTemperature = targetTemperature;
	}

	/**
	 * Getter for the {@code LampActor}'s status, that this {@code ActorState} belongs to.
	 */
	@Override
	public Double getData() {
		return targetTemperature;
	}

	/**
	 * Setter for the {@code ThermostatActor}'s target temperature, that this {@code ActorState} belongs to.
	 */
	@Override
	public void setData(Double d) {
		this.targetTemperature = d;
	}

}
