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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.daergoth.serviceapi.sensors.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

/**
 * Default implementation for {@code DummyDataGeneratorLocal}.
 */
@Singleton(name = "DummyDataGenerator")
@Startup
@DependsOn("SensorContainer")
@Local(DummyDataGeneratorLocal.class)
public class DummyDataGeneratorLocalImpl implements DummyDataGeneratorLocal {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyDataGeneratorLocal.class);
	
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
		logger.debug("Timer started, remaining time: {}", tm.getTimeRemaining());
	}

	@PostConstruct
	private void init() {
		dummiesList = sensorContainer.getDummySensors();
		startGenerating();
		logger.info("Service initialized!");
	}

	@PreDestroy
	private void destroy() {
		stopGenerating();
		logger.info("Service destroyed!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startGenerating() {
		createTimer(MIN_UPDATE_INTERVAL);
		logger.debug("Starting data generation...");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopGenerating() {
		if (tm != null) {
			tm.cancel();
		}
		logger.debug("Stopping data generation...");
	}

	/**
	 * {@inheritDoc}
	 */
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
		generateAllDummies();
	}

	/**
	 * Generates new {@code SensorData} readings for all simulated {@code Sensor}s.
	 */
	public void generateAllDummies() {
		logger.debug("Generating new data for all DummySensors...");
		dummiesList = sensorContainer.getDummySensors();
		
		for (int i = 0; i < dummiesList.size(); ++i) {
			dummiesList.get(i).generateRandomData();
		}
	}
	
	/**
	 * Setter for the {@code SensorContainerLocal} service.
	 * @param sensorContainer  the service-provider service
	 */
	public void setSensorContainer(SensorContainerLocal sensorContainer) {
		this.sensorContainer = sensorContainer;
	}

}
