package net.daergoth.core;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.daergoth.coreapi.DummySensorDTO;
import net.daergoth.coreapi.SensorDTO;
import net.daergoth.coreapi.SensorDaoLocal;

@Stateless
@Local
public class SensorDaoLocalImpl implements SensorDaoLocal {
	
	@PersistenceContext
	EntityManager em;

	public List<SensorDTO> getSensors() {
		TypedQuery<Sensor> q = em.createNamedQuery("Sensor.findAll", Sensor.class);
		List<Sensor> list = q.getResultList();
		
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
		
		return newlist;
	}

	public void addSensor(SensorDTO s) {
		/*
		Query q = em.createNativeQuery("INSERT INTO sensor(id, name, type) values(" + s.getId() + ", \"" + s.getName() +"\", \"" + s.getType() + "\")");
		q.executeUpdate();
		
		if (s.getClass().equals(DummySensorDTO.class)) {
			DummySensorDTO ds = (DummySensorDTO) s;
			Query q2 = em.createNativeQuery("INSERT INTO dummysensorinformation(sensor_id, min_data, max_data, refresh_interval) "
					+ "values(" + ds.getId() + ", \"" + ds.getMin() +"\", \"" + ds.getMax() + "\", " + ds.getInterval() + ")");
			q2.executeUpdate();
		}
		*/
		
		em.merge(SensorConverter.toEntity(s));
	}

	public void deleteSensor(long id) {
		/*
		Query q1 = em.createNativeQuery("DELETE FROM dummysensorinformation WHERE sensor_id = " + id);
		q1.executeUpdate();
		Query q2 = em.createNativeQuery("DELETE FROM sensor WHERE id = " + id);
		q2.executeUpdate();
		*/
		
		em.remove(em.find(Sensor.class, id));
	}
	
	public void deleteSensors(List<Long> ids) {
		/*
		StringBuilder stringBuilder = new StringBuilder();
		for (Long long1 : ids) {
			stringBuilder.append(long1);
			stringBuilder.append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		
		Query q1 = em.createNativeQuery("DELETE FROM dummysensorinformation WHERE sensor_id IN (" + stringBuilder.toString() + ")");
		q1.executeUpdate();
		Query q2 = em.createNativeQuery("DELETE FROM sensor WHERE id IN (" + stringBuilder.toString() + ")");
		q2.executeUpdate();
		*/
		
		for (Long id : ids) {
			deleteSensor(id);
		}
	}

	public void updateSensor(SensorDTO s) {
		/*
		Query q = em.createNativeQuery("UPDATE sensor SET name = \"" + s.getName() + "\", type = \"" + s.getType() +"\" WHERE id = " + s.getId());
		q.executeUpdate();
		*/
		
		em.merge(SensorConverter.toEntity(s));
	}

}
