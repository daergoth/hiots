package net.daergoth.service;

import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

public class DataChangeListenerLocalImpl implements DataChangeListenerLocal {

	@Override
	public void subscribeFor(SensorVO sensor, DataChangeHandler handler) {
		// TODO
	}

	@Override
	public void unsubscribeFrom(SensorVO sensor, DataChangeHandler handler) {
		// TODO 
	}

}
