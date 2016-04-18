package net.daergoth.service;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

import net.daergoth.serviceapi.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

@Singleton
@Startup
@Local(DummyDataGeneratorLocal.class)
public class DummyDataGeneratorLocalImpl implements DummyDataGeneratorLocal {
	
	@EJB
	SensorContainerLocal sensorContainer;
	
	List<DummySensorVO> dummiesList;
	
	List<Long> ellapsed = new ArrayList<>();
	
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
		setDummiesList(sensorContainer.getDummySensors());
		
		for (int i = 0; i < dummiesList.size(); ++i) {
			ellapsed.add(0l);
		}
		
		createTimer(MIN_UPDATE_INTERVAL);
	}
	
	@Timeout
	public void generateDummyData(Timer timer) {
		setDummiesList(sensorContainer.getDummySensors());

		for (int i = 0; i < dummiesList.size(); ++i) {
			if (ellapsed.get(i) >= dummiesList.get(i).getInterval()) {
				dummiesList.get(i).generateRandomData();
				ellapsed.set(i, 0l);
			} else {
				ellapsed.set(i, ellapsed.get(i) + MIN_UPDATE_INTERVAL);
			}
		}
		
		System.out.println("GENERATED!");
	}
	
	
	public void generateAllDummies() {
		for (int i = 0; i < dummiesList.size(); ++i) {
			dummiesList.get(i).generateRandomData();
		}
	}

	@Override
	public void setDummiesList(List<DummySensorVO> dl) {
		this.dummiesList = dl;
		
		ellapsed.clear();
		
		for (int i = 0; i < dummiesList.size(); ++i) {
			ellapsed.add(0l);
		}
	}

	@Override
	public void addDummy(DummySensorVO d) {
		dummiesList.add(d);
		ellapsed.add(0l);

	}

	@Override
	public void deleteDummy(DummySensorVO d) {
		ellapsed.remove(dummiesList.indexOf(d));
		dummiesList.remove(d);
	}
	


}
