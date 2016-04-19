package net.daergoth.net;

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

import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@ManagedBean(name = "setupManager")
@ViewScoped
public class SetupManager {

	@EJB
	SensorContainerLocal sensorContainer;
	
	private DefaultMenuModel menuBar;
	private String dlgHeader = "Header"; 
	
	private String minValue;
	private String maxValue;
	private String name;
	private String interval;
	private SensorType selectedType;

	private List<SensorVO> sensors;
	private List<SensorVO> selectedSensors;
	private SensorType[] sensorTypes = SensorType.values();

	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors());
		
		menuBar = new DefaultMenuModel();
		
		DefaultSubMenu sensorSubMenu = new DefaultSubMenu("New sensor");
		sensorSubMenu.setIcon("fa fa-plus");
		for (SensorType type : sensorTypes) {
			DefaultMenuItem item = new DefaultMenuItem(type.toString());
			item.setCommand("#{setupManager.setSensorType('" + type.toString() + "')}");
			sensorSubMenu.addElement(item);
		}
		menuBar.addElement(sensorSubMenu);
		
		DefaultSubMenu actorSubMenu = new DefaultSubMenu("New actor");
		actorSubMenu.setIcon("fa fa-plus");
		/*
		for (SensorTypes type : SensorTypes.values()) {
			DefaultMenuItem item = new DefaultMenuItem(type.toString());
			actorSubMenu.addElement(item);
		}
		*/
		actorSubMenu.addElement(new DefaultMenuItem("TODO"));
		menuBar.addElement(actorSubMenu);
		
		DefaultMenuItem deleteItem = new DefaultMenuItem("Delete");
		deleteItem.setCommand("#{setupManager.delete}");
		deleteItem.setIcon("fa fa-eraser");
		deleteItem.setUpdate("sensorDT");
		menuBar.addElement(deleteItem);
	}
	
	public void onRowEdit(RowEditEvent event) {
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        
    }
    
    public void createSensor() {
		switch (selectedType) {
		case Light:
			sensorContainer.addSensor(
					new DummyLightSensorVO(
							sensorContainer.getNewId(), 
							"Dummy" + name, 
							Double.parseDouble(minValue), 
							Double.parseDouble(maxValue),
							Long.parseLong(interval))
			);
			break;
		case Temperature:
			sensorContainer.addSensor(
					new DummyTemperatureSensorVO(
							sensorContainer.getNewId(), 
							"Dummy" + name, 
							Double.parseDouble(minValue), 
							Double.parseDouble(maxValue),
							Long.parseLong(interval))
			);
			break;
		default:
			
			break;
		}
		
		setSensors(sensorContainer.getSensors());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg').hide();");
    }
    
    public void setSensorType(String t) {
    	for (SensorType type : sensorTypes) {
			if (t.equals(type.toString())) {
				selectedType = type;
				setDlgHeader(type.toString());
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dlg').show();");
				break;
			}
		}
    }
    
	public void delete() {
		List<Long> ids = new ArrayList<>();
		for (SensorVO selected : selectedSensors) {
			ids.add(selected.getId());
		}
		sensorContainer.deleteSensors(ids);
		setSensors(sensorContainer.getSensors());
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

	public SensorType[] getSensor_types() {
		return sensorTypes;
	}

	public void setSensor_types(SensorType[] sensor_types) {
		this.sensorTypes = sensor_types;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public DefaultMenuModel getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(DefaultMenuModel menuBar) {
		this.menuBar = menuBar;
	}

	public SensorType getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(SensorType selectedType) {
		this.selectedType = selectedType;
	}

	public String getDlgHeader() {
		return dlgHeader;
	}

	public void setDlgHeader(String dlgHeader) {
		this.dlgHeader = dlgHeader;
	}



}
