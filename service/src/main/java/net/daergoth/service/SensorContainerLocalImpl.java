package net.daergoth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import net.daergoth.coreapi.DummySensorDTO;
import net.daergoth.coreapi.SensorDTO;
import net.daergoth.coreapi.SensorDaoLocal;
import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@Stateless
@Local(SensorContainerLocal.class)
public class SensorContainerLocalImpl implements SensorContainerLocal {
	
	@EJB
	SensorDaoLocal sensorDao;
	
	List<SensorVO> sensors = new ArrayList<>();
	
	private boolean changed = true;

	@Override
	public List<SensorVO> getSensors() {
		if (changed) {
			List<SensorDTO> dto_list = sensorDao.getSensors();
			List<SensorVO> vo_list = new ArrayList<>();
			
			for (SensorDTO sensorDTO : dto_list) {
				switch (sensorDTO.getType()) {
				case "Temperature":
					if (sensorDTO.getClass().equals(DummySensorDTO.class)) {
						DummySensorDTO dummyDTO = (DummySensorDTO) sensorDTO;
						DummyTemperatureSensorVO dummyVo = new DummyTemperatureSensorVO(dummyDTO.getId(), 
								dummyDTO.getName(), 
								dummyDTO.getMin(), 
								dummyDTO.getMax(), 
								dummyDTO.getInterval());
						vo_list.add(dummyVo);
					} else {
						TemperatureSensorVO sensorVO = new TemperatureSensorVO(sensorDTO.getId(), sensorDTO.getName());
						vo_list.add(sensorVO);
					}
					
					break;
				case "Light":
					if (sensorDTO.getClass().equals(DummySensorDTO.class)) {
						DummySensorDTO dummyDTO = (DummySensorDTO) sensorDTO;
						DummyLightSensorVO dummyVo = new DummyLightSensorVO(dummyDTO.getId(), 
								dummyDTO.getName(), 
								dummyDTO.getMin(), 
								dummyDTO.getMax(), 
								dummyDTO.getInterval());
						vo_list.add(dummyVo);
					} else {
						LightSensorVO sensorVO = new LightSensorVO(sensorDTO.getId(), sensorDTO.getName());
						vo_list.add(sensorVO);
					}
					break;
				}
			}
			
			sensors = vo_list;
			
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
		
		if (s.getClass().getSuperclass().equals(DummySensorVO.class)) {
			DummySensorVO ds = (DummySensorVO) s;
			
			DummySensorDTO newDummyDTO = new DummySensorDTO();
			newDummyDTO.setId(ds.getId());
			newDummyDTO.setName(ds.getName());
			newDummyDTO.setType(ds.getType().toString());
			newDummyDTO.setMax(ds.getMaxData());
			newDummyDTO.setMin(ds.getMinData());
			newDummyDTO.setInterval(ds.getInterval());
			sensorDao.addSensor(newDummyDTO);
		} else {
			SensorDTO newSensDTO = new SensorDTO();
			newSensDTO.setId(s.getId());
			newSensDTO.setName(s.getName());
			newSensDTO.setType(s.getType().toString());
			sensorDao.addSensor(newSensDTO);
		}
	}

	@Override
	public void updateSensor(SensorVO s) {
		changed = true;
		
		if (s.getClass().getSuperclass().equals(DummySensorVO.class)) {
			DummySensorVO ds = (DummySensorVO) s;
			
			DummySensorDTO newDummyDTO = new DummySensorDTO();
			newDummyDTO.setId(ds.getId());
			newDummyDTO.setName(ds.getName());
			newDummyDTO.setType(ds.getType().toString());
			newDummyDTO.setMax(ds.getMaxData());
			newDummyDTO.setMin(ds.getMinData());
			newDummyDTO.setInterval(ds.getInterval());
			sensorDao.updateSensor(newDummyDTO);
		} else {
			SensorDTO newSensDTO = new SensorDTO();
			newSensDTO.setId(s.getId());
			newSensDTO.setName(s.getName());
			newSensDTO.setType(s.getType().toString());
			sensorDao.updateSensor(newSensDTO);
		}
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

	@Override
	public long getNewId() {
		
		try {
			return getSensors().stream().mapToLong(s -> s.getId()).max().getAsLong() + 1;
		} catch (NoSuchElementException e) {
			return 1;
		}
		
	}
	
}
