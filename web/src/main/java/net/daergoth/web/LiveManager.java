package net.daergoth.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

@ManagedBean(name = "liveManager")
@ViewScoped
public class LiveManager {
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	@EJB
	private ActorContainerLocal actorContainer;

	private List<SensorVO> sensors;
	
	private List<ActorVO> actors;

	@PostConstruct
	public void init() {
		
		setSensors(sensorContainer.getSensors()); 
		setActors(actorContainer.getActors());
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

	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}
	
	

}
