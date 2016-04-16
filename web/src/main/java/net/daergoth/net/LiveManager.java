package net.daergoth.net;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.SensorVO;
import net.daergoth.serviceapi.dummy.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.dummy.DummySensorVO;

@ManagedBean(name = "liveManager")
@ViewScoped
public class LiveManager {
	
	@EJB
	SensorContainerLocal sensorContainer;
	
	@EJB
	DummyDataGeneratorLocal dummyDataGen;

	private List<SensorVO> sensors;

	@PostConstruct
	public void init() {
		
		setSensors(sensorContainer.getSensors());
		
		List<DummySensorVO> dummylist = new ArrayList<>();
		for (SensorVO sensor : sensors) {			
			if (sensor.getClass().getSuperclass().equals(DummySensorVO.class)) 
				dummylist.add((DummySensorVO) sensor);
		}
		dummyDataGen.setDummiesList(dummylist);

		
	}
	
	@PreDestroy
	public void destroy() {
		
	}
	
	public void refresh() {
		dummyDataGen.generateAllDummies();
	}

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}
	
	




}
