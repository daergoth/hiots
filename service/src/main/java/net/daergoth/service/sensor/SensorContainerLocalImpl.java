package net.daergoth.service.sensor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDaoLocal;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorConvertException;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

@Singleton(name = "SensorContainer")
@Startup
@Local(SensorContainerLocal.class)
public class SensorContainerLocalImpl implements SensorContainerLocal {
	 
	@EJB
	private SensorDaoLocal sensorDao;
	
	private List<SensorVO> sensors = new ArrayList<>();
	
	private boolean changed = true;

	@Override
	public List<SensorVO> getSensors() {
		if (changed) {
			List<SensorDTO> dtoList = sensorDao.getSensors();
			List<SensorVO> voList = new ArrayList<>();
			
			for (SensorDTO sensorDTO : dtoList) {
				try {
					voList.add(SensorConverter.toVO(sensorDTO));
				} catch (SensorConvertException e) {
					e.printStackTrace();
				}
			}
			
			sensors = voList;
			
			changed = false;
		}
		
		return sensors;
	}

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



	@Override
	public void addSensor(SensorVO s) {
		changed = true;
		
		sensorDao.addSensor(SensorConverter.toDTO(s));
	}

	@Override
	public void updateSensor(SensorVO s) {
		changed = true;
		
		sensorDao.updateSensor(SensorConverter.toDTO(s));
	}

	@Override
	public void deleteSensor(long id) {
		changed = true;
		
		sensorDao.deleteSensor(id);
	}

	@Override
	public void deleteSensors(List<Long> ids) {
		changed = true;
		
		sensorDao.deleteSensors(ids);
	}
	
}
