package net.daergoth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.sensors.InvalidSensorDataTypeException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

@Singleton
@Startup
@Local(DataChangeListenerLocal.class)
public class DataChangeListenerLocalImpl implements DataChangeListenerLocal {
	
	HashMap<SensorVO, ArrayList<DataChangeHandler> > subs = new HashMap<>();
	
	HashMap<Long, SensorDataVO> pastData = new HashMap<>();
	
	@Resource
	private SessionContext context;
	
	private Timer tm;
	
	public void createTimer(long interval) {
		if (tm == null)
			tm = context.getTimerService().createIntervalTimer(0, interval, new TimerConfig());
		else {
			tm.cancel();
			tm = context.getTimerService().createIntervalTimer(0, interval, new TimerConfig());
		}
	}

	
	@PostConstruct
	public void init() {
		createTimer(UPDATE_INTERVAL);
		
		for (SensorVO s : subs.keySet()) {
			pastData.put(s.getId(), s.getData());
		}
	}
	
	@Timeout
	public void checkForChange(Timer timer) {
		
		for (Map.Entry<SensorVO, ArrayList<DataChangeHandler>> entry : subs.entrySet()) {
			SensorDataVO current = entry.getKey().getData();
			SensorDataVO past = pastData.get(entry.getKey().getId());
			
			if (past != null && current != null) {
				try {
					if (past.compareTo(current) != 0) {
						changed(entry.getKey());
						pastData.put(entry.getKey().getId(), current);
					}					
					
				} catch (InvalidSensorDataTypeException e) {
					System.err.println("Sensor Type probably changed: " + entry.getKey());
					System.err.println(e.getMessage());
					pastData.put(entry.getKey().getId(), current);
				}
			} else {
				pastData.put(entry.getKey().getId(), current);
			}
		}
	}

	@Override
	public void changed(SensorVO sensor) {
		System.out.println("changed: " + sensor);
		for (DataChangeHandler handler : subs.get(sensor)) {
			handler.onChange(sensor.getData());
		}
	}
	
	@Override
	public void subscribeFor(SensorVO sensor, DataChangeHandler handler) {
		System.out.println("Subscription for: " + sensor.getName());
		if (subs.containsKey(sensor)) {
			subs.get(sensor).add(handler);
		} else {
			ArrayList<DataChangeHandler> list = new ArrayList<>();
			list.add(handler);
			subs.put(sensor, list);
		}
	}
	
	@Override
	public void subscribeFor(SensorVO sensor, List<DataChangeHandler> handlers) {
		for (DataChangeHandler handler : handlers) {
			subscribeFor(sensor, handler);
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
	
	
	
	@Override
	public void unsubscribeFrom(SensorVO sensor, List<DataChangeHandler> handlers) {
		for (DataChangeHandler handler : handlers) {
			unsubscribeFrom(sensor, handler);
		}
	}


	@Override
	public void unsubscribeAllFrom(SensorVO sensor) {
		subs.remove(sensor);
	}
	
	@Override
	public List<DataChangeHandler> getHandlersFor(SensorVO sensor) {
		return subs.get(sensor);
	}

}
