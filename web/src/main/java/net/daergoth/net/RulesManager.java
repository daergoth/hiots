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
	SensorContainerLocal sensorContainer;
	
	@EJB
	DataChangeListenerLocal changeListener;
	
	private List<SensorVO> sensors;
	
	private String selectedSensor;
	
	private String console;
	
	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors());
	}
	
	public void listenerFor() {
		System.out.println("Selected: " + selectedSensor);
		SensorVO s = sensorContainer.getSensors().stream().filter(se -> se.getName().equals(selectedSensor)).findFirst().get();
		changeListener.subscribeFor(s, new DataChangeHandler() {

			@Override
			public void onChange(SensorData newData) {
				System.out.println("CHENGED LISTENER!");
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


	public String getSelectedSensor() {
		return selectedSensor;
	}


	public void setSelectedSensor(String selectedSensor) {
		this.selectedSensor = selectedSensor;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
	
	

}
