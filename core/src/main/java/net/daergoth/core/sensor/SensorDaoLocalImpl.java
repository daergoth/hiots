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
	private EntityManager em;
	
	@Override
	public List<SensorDTO> getSensors() {
		TypedQuery<Sensor> q = em.createNamedQuery("Sensor.findAll", Sensor.class);
		return SensorConverter.toDTOs(q.getResultList());
	}
	
	@Override
	public void addSensor(SensorDTO s) {
		em.merge(SensorConverter.toEntity(s));
	}
	
	@Override
	public void deleteSensor(long id) {
		em.remove(em.find(Sensor.class, id));
	}
	
	@Override
	public void deleteSensors(List<Long> ids) {
		for (Long id : ids) {
			deleteSensor(id);
		}
	}
	
	@Override
	public void updateSensor(SensorDTO s) {
		em.merge(SensorConverter.toEntity(s));
	}

}
