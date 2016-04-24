package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class ThermostatActorState implements ActorState {
	
	private double targetTemperature;

	@Override
	public int compareTo(ActorState other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(ThermostatActorState.class)) {
			ThermostatActorState o = (ThermostatActorState) other;
			return Double.compare(targetTemperature, o.getTargetTemperature());
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}

	public double getTargetTemperature() {
		return targetTemperature;
	}

	public void setTargetTemperature(double targetTemperature) {
		this.targetTemperature = targetTemperature;
	}

}
