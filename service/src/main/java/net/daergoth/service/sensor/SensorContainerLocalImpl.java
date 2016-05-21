package net.daergoth.service.sensor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDaoLocal;
import net.daergoth.service.cobertura.CoverageIgnore;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

/**
 * Default implementation for {@code SensorContainerLocal}.
 */
@Singleton(name = "SensorContainer")
@Startup
@Local(SensorContainerLocal.class)
public class SensorContainerLocalImpl implements SensorContainerLocal {
	
	private static final Logger logger = LoggerFactory.getLogger(SensorContainerLocal.class);
	 
	@EJB
	private SensorDaoLocal sensorDao;
	
	private List<SensorVO> sensors = new ArrayList<>();
	
	private boolean changed = true;

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public List<SensorVO> getSensors() {
		if (changed) {
			List<SensorDTO> dtoList = sensorDao.getSensors();
			List<SensorVO> voList = new ArrayList<>();
			
			for (SensorDTO sensorDTO : dtoList) {
				try {
					voList.add(SensorConverter.toVO(sensorDTO));
				} catch (SensorConvertException e) {
					logger.error("Error during Sensor converting.", e);
				}
			}
			
			sensors = voList;
			
			changed = false;
		}
		
		return sensors;
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public List<DummySensorVO> getDummySensors() {
		List<DummySensorVO> dummyList = new ArrayList<>();
		
		for (SensorVO sensor : getSensors()) {
			if (sensor.getClass().getSuperclass().equals(DummySensorVO.class)) {
				dummyList.add((DummySensorVO) sensor);
			}
		}
		
		return dummyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void addSensor(SensorVO s) {
		changed = true;
		
		sensorDao.addSensor(SensorConverter.toDTO(s));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void updateSensor(SensorVO s) {
		changed = true;
		
		sensorDao.updateSensor(SensorConverter.toDTO(s));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void deleteSensor(long id) {
		changed = true;
		
		sensorDao.deleteSensor(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void deleteSensors(List<Long> ids) {
		changed = true;
		
		sensorDao.deleteSensors(ids);
	}
	
}
