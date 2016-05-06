package net.daergoth.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import net.daergoth.serviceapi.DataChangeHandler;
import net.daergoth.serviceapi.DataChangeListenerLocal;
import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@ManagedBean(name = "setupManager")
@ViewScoped
public class SetupManager {
	
	@EJB
	DataChangeListenerLocal changeListener;

	@EJB
	SensorContainerLocal sensorContainer;
	
	@EJB
	ActorContainerLocal actorContainer;
	
	private DefaultMenuModel menuBar;
	
	private String sensorDlgHeader = "Header";
	
	private String actorDlgHeader = "Header";
	
	private String sensorMinValue;
	private String sensorMaxValue;
	//private String sensorInterval;
	private String sensorName;
	private SensorType selectedSensorType;
	
	private String actorName;
	private ActorType selectedActorType; 

	private List<SensorVO> sensors;
	private List<SensorVO> selectedSensors;
	private SensorType[] sensorTypes = SensorType.values();
	
	private List<ActorVO> actors;
	private List<ActorVO> selectedActors;
	private ActorType[] actorTypes = ActorType.values();

	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors());
		setActors(actorContainer.getActors());
		
		menuBar = new DefaultMenuModel();
		
		// Creating "New sensor" submenu
		DefaultSubMenu sensorSubMenu = new DefaultSubMenu("New sensor");
		sensorSubMenu.setIcon("fa fa-plus");
		for (SensorType type : sensorTypes) {
			DefaultMenuItem item = new DefaultMenuItem(type.toString());
			item.setCommand("#{setupManager.setSensorCreateType('" + type.toString() + "')}");
			sensorSubMenu.addElement(item);
		}
		menuBar.addElement(sensorSubMenu);
		
		// Creating "New actor" submenu
		DefaultSubMenu actorSubMenu = new DefaultSubMenu("New actor");
		actorSubMenu.setIcon("fa fa-plus");
		
		for (ActorType type : actorTypes) {
			DefaultMenuItem item = new DefaultMenuItem(type.toString());
			item.setCommand("#{setupManager.setActorCreateType('" + type.toString() + "')}");
			actorSubMenu.addElement(item);
		}
		menuBar.addElement(actorSubMenu);
		
		DefaultMenuItem deleteItem = new DefaultMenuItem("Delete");
		deleteItem.setCommand("#{setupManager.delete}");
		deleteItem.setIcon("fa fa-eraser");
		deleteItem.setUpdate("sensorDT, actorDT");
		menuBar.addElement(deleteItem);
	}
	
	public void onRowEditSensor(RowEditEvent event) {
		SensorVO s = (SensorVO) event.getObject();
		List<DataChangeHandler> handlers = changeListener.getHandlersFor(s.getId());
		if (handlers != null) {
			changeListener.unsubscribeAllFrom(s.getId());
			sensorContainer.updateSensor(s);
			changeListener.subscribeFor(
					sensorContainer.getSensors().stream().filter(se -> se.getId() == s.getId()).findFirst().get().getId(),
					handlers);
		} else {
			sensorContainer.updateSensor(s);
		}
    }
    
	public void onRowEditActor(RowEditEvent event) {
		actorContainer.updateActor((ActorVO) event.getObject());
    }
    
    public void createSensor() {
		switch (selectedSensorType) {
		case Light:
			sensorContainer.addSensor(
					new DummyLightSensorVO(
							sensorContainer.getNewId(), 
							"Dummy" + sensorName, 
							Double.parseDouble(sensorMinValue), 
							Double.parseDouble(sensorMaxValue),
							1000)
			);
			break;
		case Temperature:
			sensorContainer.addSensor(
					new DummyTemperatureSensorVO(
							sensorContainer.getNewId(), 
							"Dummy" + sensorName, 
							Double.parseDouble(sensorMinValue), 
							Double.parseDouble(sensorMaxValue),
							1000)
			);
			break;
		default:
			
			break;
		}
		
		setSensors(sensorContainer.getSensors());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgSensor').hide();");
    }
    
    public void createActor() {
    	switch (selectedActorType) {
		case Lamp:
			actorContainer.addActor(new DummyLampActorVO(
					actorContainer.getNewId(),
					"Dummy" + actorName)
			);
			break;
		case Thermostat:
			actorContainer.addActor(new DummyThermostatActorVO(
					actorContainer.getNewId(),
					"Dummy" + actorName)
			);
			break;
		default:
			
			break;
    	}
    	setActors(actorContainer.getActors());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgActor').hide();");
    }
    
    public void setSensorCreateType(String t) {
    	selectedSensorType = SensorType.valueOf(t);
    	sensorDlgHeader = "New " + selectedSensorType.toString() + " sensor";
    	RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgSensor').show();");
    }
    
    public void setActorCreateType(String t) {
    	selectedActorType = ActorType.valueOf(t);
    	actorDlgHeader = "New " + selectedActorType.toString() + " actor";
    	RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgActor').show();");
    }
    
	public void delete() {
		List<Long> ids = new ArrayList<>();
		for (SensorVO selected : selectedSensors) {
			ids.add(selected.getId());
		}
		sensorContainer.deleteSensors(ids);
		setSensors(sensorContainer.getSensors());
		ids.clear();
		for (ActorVO selected : selectedActors) {
			ids.add(selected.getId());
		}
		actorContainer.deleteActors(ids);
		setActors(actorContainer.getActors());
	}

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}

	public List<SensorVO> getSelectedSensors() {
		return selectedSensors;
	}

	public void setSelectedSensors(List<SensorVO> selected_sensors) {
		this.selectedSensors = selected_sensors;
	}

	public DefaultMenuModel getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(DefaultMenuModel menuBar) {
		this.menuBar = menuBar;
	}

	public String getSensorDlgHeader() {
		return sensorDlgHeader;
	}

	public void setSensorDlgHeader(String sensorDlgHeader) {
		this.sensorDlgHeader = sensorDlgHeader;
	}

	public String getActorDlgHeader() {
		return actorDlgHeader;
	}

	public void setActorDlgHeader(String actorDlgHeader) {
		this.actorDlgHeader = actorDlgHeader;
	}

	public String getSensorMinValue() {
		return sensorMinValue;
	}

	public void setSensorMinValue(String sensorMinValue) {
		this.sensorMinValue = sensorMinValue;
	}

	public String getSensorMaxValue() {
		return sensorMaxValue;
	}

	public void setSensorMaxValue(String sensorMaxValue) {
		this.sensorMaxValue = sensorMaxValue;
	}

	/*
	public String getSensorInterval() {
		return sensorInterval;
	}

	public void setSensorInterval(String sensorInterval) {
		this.sensorInterval = sensorInterval;
	}
	*/

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public SensorType getSelectedSensorType() {
		return selectedSensorType;
	}

	public void setSelectedSensorType(SensorType selectedSensorType) {
		this.selectedSensorType = selectedSensorType;
	}

	public String getActorName() {
		return actorName;
	}

	public SensorType[] getSensorTypes() {
		return sensorTypes;
	}

	public void setSensorTypes(SensorType[] sensorTypes) {
		this.sensorTypes = sensorTypes;
	}

	public ActorType[] getActorTypes() {
		return actorTypes;
	}

	public void setActorTypes(ActorType[] actorTypes) {
		this.actorTypes = actorTypes;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public ActorType getSelectedActorType() {
		return selectedActorType;
	}

	public void setSelectedActorType(ActorType selectedActorType) {
		this.selectedActorType = selectedActorType;
	}
	
	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}

	public List<ActorVO> getSelectedActors() {
		return selectedActors;
	}

	public void setSelectedActors(List<ActorVO> selectedActors) {
		this.selectedActors = selectedActors;
	}
	
}
