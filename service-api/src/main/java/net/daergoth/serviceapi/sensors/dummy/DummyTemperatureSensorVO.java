package net.daergoth.serviceapi.sensors.dummy;

import java.util.Random;

import net.daergoth.serviceapi.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.datatypes.SensorData;
import net.daergoth.serviceapi.datatypes.TemperatureData;

public class DummyTemperatureSensorVO extends DummySensorVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -16359446176096506L;

	private Random rand = new Random();
	
	private boolean status = true;
	
	public DummyTemperatureSensorVO(long id, String name, double minData, double maxData, long interval) {
		super(id, name, minData, maxData, interval);
		this.Type = "Temperature";
	}

	@Override
	public TemperatureData getData() {
		return (TemperatureData) data;
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(TemperatureData.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
	}	
	
	public void generateRandomData() {
		
		try {
			setData(new TemperatureData(rand.nextFloat()*(maxData-minData)+minData));
		} catch (InvalidSensorDataTypeException e) {
			e.printStackTrace();
		}
	}

	public boolean isWorking() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
