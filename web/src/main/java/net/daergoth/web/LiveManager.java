package net.daergoth.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

@ManagedBean(name = "liveManager")
@ViewScoped
public class LiveManager {
	
	@EJB
	SensorContainerLocal sensorContainer;
	

	private List<SensorVO> sensors;

	@PostConstruct
	public void init() {
		
		setSensors(sensorContainer.getSensors()); 
		
	}
	
	@PreDestroy
	public void destroy() {
		
	}
	
	public void refresh() {
		
	}
	
	public void forceRefresh() {
		setSensors(sensorContainer.getSensors());
	}

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}
	
	




}
