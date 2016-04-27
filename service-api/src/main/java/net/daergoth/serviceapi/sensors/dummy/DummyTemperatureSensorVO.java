package net.daergoth.serviceapi.sensors.dummy;

import java.util.Random;

import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

public class DummyTemperatureSensorVO extends DummySensorVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -16359446176096506L;

	private Random rand = new Random();
	
	public DummyTemperatureSensorVO(long id, String name, double minData, double maxData, long interval) {
		super(id, name, minData, maxData, interval);
		this.Type = SensorType.Temperature;
	}

	@Override
	public TemperatureDataVO getData() {
		return (TemperatureDataVO) data;
	}

	@Override
	public void setData(SensorDataVO d) throws InvalidSensorDataTypeException {
		if (d.getClass().equals(TemperatureDataVO.class)) {
			data = d;
		}
		else
			throw new InvalidSensorDataTypeException("TemperatureData expected!");
	}	
	
	public void generateRandomData() {
		
		try {
			setData(new TemperatureDataVO(rand.nextFloat()*(maxData-minData)+minData));
		} catch (InvalidSensorDataTypeException e) {
			e.printStackTrace();
		}
	}
	
}
