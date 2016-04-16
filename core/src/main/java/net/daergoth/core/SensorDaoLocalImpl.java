package net.daergoth.core;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.daergoth.coreapi.DummySensorDTO;
import net.daergoth.coreapi.SensorDTO;
import net.daergoth.coreapi.SensorDaoLocal;

@Stateless
@Local
public class SensorDaoLocalImpl implements SensorDaoLocal {
	
	@PersistenceContext
	EntityManager em;

	public List<SensorDTO> getSensors() {
		Query q = em.createNativeQuery("SELECT * FROM sensor", Sensor.class);
		List<Sensor> list = q.getResultList();
		
		List<SensorDTO> newlist = new ArrayList<SensorDTO>();
		
		for (Sensor sensor : list) {
			Query q2 = em.createNativeQuery("SELECT * FROM dummysensorinformation WHERE sensor_id = " + sensor.getId(), DummySensorInformation.class);
			List<DummySensorInformation> dummylist = q2.getResultList();
			
			if (dummylist.isEmpty()) {
				SensorDTO newSens = new SensorDTO();
				newSens.setId(sensor.getId());
				newSens.setName(sensor.getName());
				newSens.setType(sensor.getType());
				newlist.add(newSens);
			} else {
				DummySensorInformation dummyInfo = dummylist.get(0);
				DummySensorDTO newDummy = new DummySensorDTO();
				newDummy.setId(sensor.getId());
				newDummy.setName(sensor.getName());
				newDummy.setType(sensor.getType());
				newDummy.setMin(dummyInfo.getMin_data());
				newDummy.setMax(dummyInfo.getMax_data());
				newDummy.setInterval(dummyInfo.getRefresh_interval());
				newlist.add(newDummy);
			}
			
			
		}
		
		return newlist;
	}

	public void addSensor(SensorDTO s) {
		Query q = em.createNativeQuery("INSERT INTO sensor(id, name, type) values(" + s.getId() + ", \"" + s.getName() +"\", \"" + s.getType() + "\")");
		q.executeUpdate();
		
		if (s.getClass().equals(DummySensorDTO.class)) {
			DummySensorDTO ds = (DummySensorDTO) s;
			Query q2 = em.createNativeQuery("INSERT INTO dummysensorinformation(sensor_id, min_data, max_data, refresh_interval) "
					+ "values(" + ds.getId() + ", \"" + ds.getMin() +"\", \"" + ds.getMax() + "\", " + ds.getInterval() + ")");
			q2.executeUpdate();
		}
	}

	public void deleteSensor(long id) {
		Query q1 = em.createNativeQuery("DELETE FROM dummysensorinformation WHERE sensor_id = " + id);
		q1.executeUpdate();
		Query q2 = em.createNativeQuery("DELETE FROM sensor WHERE id = " + id);
		q2.executeUpdate();
	}
	
	public void deleteSensors(List<Long> ids) {
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
	}

	public void updateSensor(SensorDTO s) {
		Query q = em.createNativeQuery("UPDATE sensor SET name = \"" + s.getName() + "\", type = \"" + s.getType() +"\" WHERE id = " + s.getId());
		q.executeUpdate();
	}

}
