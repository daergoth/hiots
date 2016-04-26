package net.daergoth.net;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorData;

@ManagedBean(name = "rulesManager")
@ViewScoped
public class RulesManager {
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	@EJB
	DataChangeListenerLocal changeListener;

	private List<SensorVO> sensors;
	
	private SensorVO selectedSensor;
	
	private String console = "Basic:";
	
	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors());
		
	}
	public void listenerFor() {
		System.out.println("Selected: " + selectedSensor);
		changeListener.subscribeFor(selectedSensor, new DataChangeHandler() {

			@Override
			public void onChange(SensorData newData) {
				System.out.println("CHANGED!");
			}
			
			
		});
		System.out.println("listener cerated.");
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}
	
	public List<SensorVO> getSensors() {
		return sensors;
	}


	public SensorVO getSelectedSensor() {
		return selectedSensor;
	}


	public void setSelectedSensor(SensorVO selectedSensor) {
		this.selectedSensor = selectedSensor;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public SensorContainerLocal getSensorContainer() {
		return sensorContainer;
	}

	public void setSensorContainer(SensorContainerLocal sensorContainer) {
		this.sensorContainer = sensorContainer;
	}
	
	

}
