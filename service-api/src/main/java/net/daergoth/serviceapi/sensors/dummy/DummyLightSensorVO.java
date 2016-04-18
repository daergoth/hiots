package net.daergoth.serviceapi.sensors.dummy;

import java.util.Random;

import net.daergoth.serviceapi.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.datatypes.LightData;
import net.daergoth.serviceapi.datatypes.SensorData;

public class DummyLightSensorVO extends DummySensorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Random rand = new Random();
	
	private boolean status = true;
	
	public DummyLightSensorVO(long id, String name, double min, double max, long interval) {
		super(id, name, min, max, interval);
		this.Type = "Light";
	}
	
	@Override
	public void generateRandomData() {
		try {
			setData(new LightData(rand.nextFloat()*(maxData-minData)+minData));
		} catch (InvalidSensorDataTypeException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LightData getData() {
		return (LightData) data;
	}

	@Override
	public void setData(SensorData d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(LightData.class)) {
			data = d;
			System.out.println("Set data to " + d + "  @" + getName());
		}
		else
			throw new InvalidSensorDataTypeException("LightData expected!");
	}	
	
	public boolean isWorking() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
