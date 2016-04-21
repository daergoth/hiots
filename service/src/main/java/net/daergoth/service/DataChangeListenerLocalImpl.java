package net.daergoth.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

@Singleton
@Startup
@Local(DataChangeListenerLocal.class)
public class DataChangeListenerLocalImpl implements DataChangeListenerLocal {
	
	HashMap<SensorVO, ArrayList<DataChangeHandler> > subs = new HashMap<>();

	@Override
	public void changed(SensorVO sensor) {
		for (DataChangeHandler handler : subs.get(sensor)) {
			handler.onChange(sensor.getData());
		}
	}
	
	@Override
	public void subscribeFor(SensorVO sensor, DataChangeHandler handler) {
		if (subs.containsKey(sensor)) {
			subs.get(sensor).add(handler);
		} else {
			ArrayList<DataChangeHandler> list = new ArrayList<>();
			list.add(handler);
			subs.put(sensor, list);
		}
	}

	@Override
	public void unsubscribeFrom(SensorVO sensor, DataChangeHandler handler) {
		if (subs.containsKey(sensor)) {
			if (subs.get(sensor).size() <= 1) {
				subs.remove(sensor);
			} else {
				subs.get(sensor).remove(handler);
			}
		}
	}

}
