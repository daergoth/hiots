package net.daergoth.service.sensor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;

import net.daergoth.serviceapi.sensors.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

@Singleton(name = "DummyDataGenerator")
@Startup
@DependsOn("SensorContainer")
@Local(DummyDataGeneratorLocal.class)
public class DummyDataGeneratorLocalImpl implements DummyDataGeneratorLocal {

	@EJB
	private SensorContainerLocal sensorContainer;

	private List<DummySensorVO> dummiesList;

	@Resource
	private SessionContext context;

	private Timer tm;

	private void createTimer(long interval) {
		if (tm == null)
			tm = context.getTimerService().createIntervalTimer(0, interval, new TimerConfig());
		else {
			tm.cancel();
			tm = context.getTimerService().createIntervalTimer(0, interval, new TimerConfig());
		}
	}

	@PostConstruct
	private void init() {
		System.out.println("DummyDataGenerator @PostConstruct");
		setDummiesList(sensorContainer.getDummySensors());

		startGenerating();
	}

	@PreDestroy
	private void destroy() {
		System.out.println("DummyDataGenerator @PreDestroy");
		
		stopGenerating();
	}

	@Override
	public void startGenerating() {
		createTimer(MIN_UPDATE_INTERVAL);
	}

	@Override
	public void stopGenerating() {
		if (tm != null) {
			tm.cancel();
		}
	}

	@Override
	public boolean isGenerating() {
		try {
			if (tm != null) {				
				tm.getNextTimeout();
			} else {
				return false;
			}
		} catch (NoSuchObjectLocalException e) {
			return false;
		}
		
		return true;
	}

	@Timeout
	private void generateDummyData(Timer timer) {
		setDummiesList(sensorContainer.getDummySensors());

		generateAllDummies();
	}

	public void generateAllDummies() {
		for (int i = 0; i < dummiesList.size(); ++i) {
			dummiesList.get(i).generateRandomData();
		}
	}

	private void setDummiesList(List<DummySensorVO> dl) {
		this.dummiesList = dl;
	}
	
	public void setSensorContainer(SensorContainerLocal sensorContainer) {
		this.sensorContainer = sensorContainer;
	}

}
