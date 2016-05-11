package net.daergoth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
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
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;

@Singleton(name = "DataChangeListener")
@Startup
@DependsOn("DummyDataGenerator")
@Local(DataChangeListenerLocal.class)
public class DataChangeListenerLocalImpl implements DataChangeListenerLocal {
	
	private HashMap<SensorVO, ArrayList<DataChangeHandler> > subs = new HashMap<>();
	
	private HashMap<Long, SensorDataVO> pastData = new HashMap<>();
	
	@Resource
	private SessionContext context;
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
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
		System.out.println("DataChangeListener @PostConstruct");
		createTimer(UPDATE_INTERVAL);
		
		for (SensorVO s : subs.keySet()) {
			pastData.put(s.getId(), s.getData());
		}
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("DataChangeListener @PreDestroy");
		if (tm != null) {
			tm.cancel();
		}
	}
	
	@Timeout
	public void checkForChange(Timer timer) throws Exception {
		
		//System.out.println("DataChangeListener checkForChange start");
		
		for (Map.Entry<SensorVO, ArrayList<DataChangeHandler>> entry : subs.entrySet()) {
			SensorDataVO current = entry.getKey().getData();
			SensorDataVO past = pastData.get(entry.getKey().getId());
			
			System.out.println("DataChangeListener checkForChange (name, past, current): " + entry.getKey().getName() + ", " + past + ", " + current);
			
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
	public void changed(SensorVO sensor) throws Exception {
		System.out.println("DataChangeListener changed: " + sensor.getName());
		for (DataChangeHandler handler : subs.get(sensor)) {
			handler.onChange(sensor.getData());
		}
	}
	
	@Override
	public void subscribeFor(Long sensorId, DataChangeHandler handler) {
		SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == sensorId).findFirst().get();
		System.out.println("Subscription for: " + sensor.getName());
		if (subs.containsKey(sensor)) {
			subs.get(sensor).add(handler);
		} else {
			ArrayList<DataChangeHandler> list = new ArrayList<>();
			list.add(handler);
			subs.put(sensor, list);
		}
		pastData.put(sensor.getId(), sensor.getData());
		System.out.println("DataChangeListener subscribeFor sensorClass: " + sensor.getClass());
		System.out.println("DataChangeListener subscribeFor sensorData: " + sensor.getData());
		System.out.println("DataChangeListener subscribeFor pastData: " + pastData.get(sensor.getId()));
	}
	
	@Override
	public void subscribeFor(Long sensorId, List<DataChangeHandler> handlers) {
		for (DataChangeHandler handler : handlers) {
			subscribeFor(sensorId, handler);
		}
	}

	@Override
	public void unsubscribeFrom(Long sensorId, DataChangeHandler handler) {
		SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == sensorId).findFirst().get();
		System.out.println("Unsubscription for: " + sensor.getName());
		if (subs.containsKey(sensor)) {
			if (subs.get(sensor).size() <= 1) {
				subs.remove(sensor);
			} else {
				subs.get(sensor).remove(handler);
			}
		}
		pastData.remove(sensor.getId());
	}
	
	
	
	@Override
	public void unsubscribeFrom(Long sensorId, List<DataChangeHandler> handlers) {
		if (handlers != null)
			for (DataChangeHandler handler : handlers) {
				unsubscribeFrom(sensorId, handler);
			}
	}


	@Override
	public void unsubscribeAllFrom(Long sensorId) {
		SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == sensorId).findFirst().get();
		subs.remove(sensor);
	}
	
	@Override
	public List<DataChangeHandler> getHandlersFor(Long sensorId) {
		SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == sensorId).findFirst().get();
		return subs.get(sensor);
	}

}
