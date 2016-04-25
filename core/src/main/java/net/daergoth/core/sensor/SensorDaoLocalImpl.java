package net.daergoth.core.sensor;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.daergoth.coreapi.sensor.SensorDTO;
import net.daergoth.coreapi.sensor.SensorDaoLocal;

@Stateless
@Local
public class SensorDaoLocalImpl implements SensorDaoLocal {
	
	@PersistenceContext
	EntityManager em;

	public List<SensorDTO> getSensors() {
		TypedQuery<Sensor> q = em.createNamedQuery("Sensor.findAll", Sensor.class);
		List<Sensor> list = q.getResultList();
		
		List<SensorDTO> nlist = SensorConverter.toDTOs(list);
		
		/*
		List<SensorDTO> newlist = new ArrayList<SensorDTO>();
		
		for (Sensor sensor : list) {
			if (sensor.getDummyInfo() == null) {
				SensorDTO newSens = new SensorDTO();
				newSens.setId(sensor.getId());
				newSens.setName(sensor.getName());
				newSens.setType(sensor.getType());
				newlist.add(newSens);
			} else {
				DummySensorInformation dummyInfo = sensor.getDummyInfo();
				DummySensorDTO newDummy = new DummySensorDTO();
				newDummy.setId(sensor.getId());
				newDummy.setName(sensor.getName());
				newDummy.setType(sensor.getType());
				newDummy.setMin(dummyInfo.getMinData());
				newDummy.setMax(dummyInfo.getMaxData());
				newDummy.setInterval(dummyInfo.getRefreshInterval());
				newlist.add(newDummy);
			}
		}
		*/
		
		return nlist;
	}

	public void addSensor(SensorDTO s) {
		em.merge(SensorConverter.toEntity(s));
	}

	public void deleteSensor(long id) {
		em.remove(em.find(Sensor.class, id));
	}
	
	public void deleteSensors(List<Long> ids) {
		for (Long id : ids) {
			deleteSensor(id);
		}
	}

	public void updateSensor(SensorDTO s) {
		em.merge(SensorConverter.toEntity(s));
	}

}
