package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class ThermostatActorStateVO implements ActorStateVO {
	
	private double targetTemperature;

	@Override
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(ThermostatActorStateVO.class)) {
			ThermostatActorStateVO o = (ThermostatActorStateVO) other;
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
