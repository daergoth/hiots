package net.daergoth.serviceapi.actors.states;

import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;

public class ThermostatActorStateVO extends ActorStateVO {
	
	private double targetTemperature;
	
	public int compareTo(ActorStateVO other) throws InvalidActorStateTypeException {
		if (other.getClass().equals(ThermostatActorStateVO.class)) {
			ThermostatActorStateVO o = (ThermostatActorStateVO) other;
			return Double.compare(targetTemperature, o.getTargetTemperature());
		} else {
			throw new InvalidActorStateTypeException("ThermostatActorState expected!");
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(targetTemperature);
		sb.append("°C");
		return sb.toString();
	}

	public double getTargetTemperature() {
		return targetTemperature;
	}

	public void setTargetTemperature(double targetTemperature) {
		this.targetTemperature = targetTemperature;
	}

	@Override
	public Double getData() {
		return targetTemperature;
	}

	@Override
	public void setData(Double d) {
		this.targetTemperature = d;
	}

}
