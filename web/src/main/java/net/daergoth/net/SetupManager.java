package net.daergoth.net;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import net.daergoth.serviceapi.SensorContainerLocal;
import net.daergoth.serviceapi.SensorVO;
import net.daergoth.serviceapi.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.dummy.DummyTemperatureSensorVO;



@ManagedBean(name = "setupManager")
@ViewScoped
public class SetupManager {

	@EJB
	SensorContainerLocal sensorContainer;
	
	private String max_temp = "25", min_temp = "20";
	private String max_light = "10", min_light = "0";
	private String temp_name, light_name;

	private List<SensorVO> sensors;
	
	private List<SensorVO> selected_sensors;
	
	private List<String> sensor_types = new ArrayList<String>();

	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors());
		sensor_types.add("Temperature");
		sensor_types.add("Light");
	}
	
	public void onRowEdit(RowEditEvent event) {
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        
    }
    
	public void delete() {
		List<Long> ids = new ArrayList<>();
		for (SensorVO selected : selected_sensors) {
			ids.add(selected.getId());
		}
		sensorContainer.deleteSensors(ids);
		setSensors(sensorContainer.getSensors());
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}

	public List<SensorVO> getSelected_sensors() {
		return selected_sensors;
	}

	public void setSelected_sensors(List<SensorVO> selected_sensors) {
		this.selected_sensors = selected_sensors;
	}

	public List<String> getSensor_types() {
		return sensor_types;
	}

	public void setSensor_types(List<String> sensor_types) {
		this.sensor_types = sensor_types;
	}

	public void newTemperature() {
		sensorContainer.addSensor(
				new DummyTemperatureSensorVO(
						sensorContainer.getNewId(), 
						"Dummy" + temp_name, 
						Double.parseDouble(min_temp), 
						Double.parseDouble(max_temp),
						2000)
		);
		setSensors(sensorContainer.getSensors());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg_temp').hide();");
	}
	
	public void newLight() {
		sensorContainer.addSensor(
				new DummyLightSensorVO(
						sensorContainer.getNewId(), 
						"Dummy" + light_name, 
						Double.parseDouble(min_light), 
						Double.parseDouble(max_light),
						2000)
		);
		setSensors(sensorContainer.getSensors());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg_light').hide();");
	}
	
	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_light() {
		return max_light;
	}

	public void setMax_light(String max_light) {
		this.max_light = max_light;
	}

	public String getMin_light() {
		return min_light;
	}

	public void setMin_light(String min_light) {
		this.min_light = min_light;
	}

	public String getTemp_name() {
		return temp_name;
	}

	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}

	public String getLight_name() {
		return light_name;
	}

	public void setLight_name(String light_name) {
		this.light_name = light_name;
	}



}
