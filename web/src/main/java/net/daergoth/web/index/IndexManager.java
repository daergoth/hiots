package net.daergoth.web.index;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import net.daergoth.serviceapi.monitor.OverviewLayoutContainerLocal;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementVO;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@ManagedBean(name = "indexManager")
@ViewScoped
public class IndexManager {
	
	private static final int DASHBOARD_COLUMN_NUM = 3;
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	@EJB
	private OverviewLayoutContainerLocal layoutContainer;
	
	private static Application application;
	
	private Dashboard dashboard;
	
	private DefaultDashboardModel dashboardModel;
	
	private List<OverviewLayoutVO> layouts;
	
	private OverviewLayoutVO selectedLayout;
	
	private List<SensorVO> tempSensors;
	
	private String newLayoutName = "";
    
    @PostConstruct
    public void init() {
		application = FacesContext.getCurrentInstance().getApplication();
		
		if (layoutContainer.getLayouts().size() < 1) {
			newLayoutName = "Default layout";
			newLayout();
			newLayoutName = "";
		}
		selectedLayout = layoutContainer.getLayouts().get(0);			
		
		setLayouts(layoutContainer.getLayouts());
		
		System.out.println(layouts);
		
		dashboard = (Dashboard) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
    	
		dashboardModel = new DefaultDashboardModel();
		
		for (int i = 0; i < DASHBOARD_COLUMN_NUM; ++i) {
			dashboardModel.addColumn(new DefaultDashboardColumn());
		}
		dashboard.setModel(dashboardModel);
		
		
        
        tempSensors = sensorContainer.getSensors().stream().filter(s -> s.getType() == SensorType.Temperature).collect(Collectors.toList());
    }
    
    public void newLayout () {
    	
    	OverviewLayoutVO layout = new OverviewLayoutVO();
    	layout.setId(0l);
    	layout.setName(newLayoutName);
    	layout.setElements(new ArrayList<OverviewLayoutElementVO>());
    	layoutContainer.addLayout(layout);
    }
    
    public void renameLayout () {
    	System.out.println(selectedLayout.getName());
    	selectedLayout.setName(newLayoutName);
    	layoutContainer.updateLayout(selectedLayout);
    }
    
    public void removeLayout () {
    	System.out.println(selectedLayout.getName());
    	layoutContainer.deleteLayout(selectedLayout.getId());
    }
    
    public void newTemperatureWidget(String id) {
    	SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == Long.parseLong(id)).findFirst().get();
    	TemperatureWidget tw;
    	if (sensor instanceof TemperatureSensorVO) {
    		tw = new TemperatureWidget((TemperatureSensorVO) sensor, application);
    	} else {
    		tw = new TemperatureWidget((DummyTemperatureSensorVO) sensor, application);
    	}
    	
    	dashboard.getChildren().add(tw.getAsPanel());
    	dashboardModel.getColumn(0).addWidget(tw.getPanelId());
    }
    
    public void newLightWidget(String id) {
    	SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == Long.parseLong(id)).findFirst().get();
    	LightWidget tw;
    	if (sensor instanceof LightSensorVO) {
    		tw = new LightWidget((LightSensorVO) sensor, application);
    	} else {
    		tw = new LightWidget((DummyLightSensorVO) sensor, application);
    	}
    	
    	dashboard.getChildren().add(tw.getAsPanel());
    	dashboardModel.getColumn(0).addWidget(tw.getPanelId());
    }
    
    public void newLampWidget(String id) {
    	
    }
    
    public void newThermostatWidget(String id) {
    	
    }
    
    public List<SensorVO> getSensorByType(String type) {
    	return sensorContainer.getSensors().stream().filter(s -> s.getType() == SensorType.valueOf(type)).collect(Collectors.toList());
    }
    
    public void refresh () {
    	
    }
    
    public void handleReorder() {
    	
    }

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public List<SensorVO> getTempSensors() {
		return tempSensors;
	}

	public void setTempSensors(List<SensorVO> tempSensors) {
		this.tempSensors = tempSensors;
	}

	public OverviewLayoutVO getSelectedLayout() {
		return selectedLayout;
	}

	public void setSelectedLayout(OverviewLayoutVO selectedLayout) {
		this.selectedLayout = selectedLayout;
	}

	public String getNewLayoutName() {
		return newLayoutName;
	}

	public void setNewLayoutName(String newLayoutName) {
		this.newLayoutName = newLayoutName;
	}

	public OverviewLayoutContainerLocal getLayoutContainer() {
		return layoutContainer;
	}

	public void setLayoutContainer(OverviewLayoutContainerLocal layoutContainer) {
		this.layoutContainer = layoutContainer;
	}

	public List<OverviewLayoutVO> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<OverviewLayoutVO> layouts) {
		this.layouts = layouts;
	}
    
    

}
