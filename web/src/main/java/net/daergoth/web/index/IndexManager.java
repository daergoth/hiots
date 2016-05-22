package net.daergoth.web.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorType;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.monitor.OverviewLayoutContainerLocal;
import net.daergoth.serviceapi.monitor.OverviewLayoutElementType;
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
	private ActorContainerLocal actorContainer;
	
	@EJB
	private OverviewLayoutContainerLocal layoutContainer;
	
	private static Application application;
	
	private Dashboard dashboard;
	
	private DefaultDashboardModel dashboardModel;
	
	private HashMap<String, IndexWidget> widgets;
	
	private List<OverviewLayoutVO> layouts;
	
	private OverviewLayoutVO selectedLayout;
	
	private String newLayoutName;
	
	private String renameLayoutName;
    
    @PostConstruct
    public void init() {
		application = FacesContext.getCurrentInstance().getApplication();
		
		widgets = new HashMap<>();
		
		if (layoutContainer.getLayouts().size() < 1) {
			newLayoutName = "Default layout";
			newLayout();
			newLayoutName = "";
		}
		
		setLayouts(layoutContainer.getLayouts());
		selectedLayout = layouts.get(0);			
		
		dashboard = (Dashboard) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
    	
		dashboardModel = new DefaultDashboardModel();
		
		for (int i = 0; i < DASHBOARD_COLUMN_NUM; ++i) {
			dashboardModel.addColumn(new DefaultDashboardColumn());
		}
		dashboard.setModel(dashboardModel);
		
		loadLayout();
    }
    
    public void loadLayout() {
    	HashMap<IndexWidget, Integer> toRender = new HashMap<>();
    	System.out.println("LoadLayout list");
    	
		for (OverviewLayoutElementVO e : selectedLayout.getElements()) {
			
    		switch (e.getType()) {
			case Actor:
				switch(e.getActor().getType()) {
				case Lamp:
					ActorVO la = actorContainer.getActors().stream().filter(a -> a.getId() == e.getActor().getId()).findFirst().get(); 
					
			    	LampWidget lw;
			    	if (la instanceof LampActorVO) {
			    		lw = new LampWidget(e.getId(), (LampActorVO) la, application, this);
			    	} else {
			    		lw = new LampWidget(e.getId(), (DummyLampActorVO) la, application, this);
			    	}
			    	
			    	System.out.println(lw.getPanelId());
			    	
			    	if (!widgets.containsKey(lw.getPanelId())) {
			    		toRender.put(lw,e.getColumn());		    		
			    	}
			    	
			    	
					break;
				case Thermostat:
					ActorVO ta = actorContainer.getActors().stream().filter(a -> a.getId() == e.getActor().getId()).findFirst().get();
					
			    	ThermostatWidget tw;
			    	if (ta instanceof LampActorVO) {
			    		tw = new ThermostatWidget(e.getId(), (ThermostatActorVO) ta, application, this);
			    	} else {
			    		tw = new ThermostatWidget(e.getId(), (DummyThermostatActorVO) ta, application, this);
			    	}
			    	System.out.println(tw.getPanelId());

			    	if (!widgets.containsKey(tw.getPanelId())) {
			    		toRender.put(tw,e.getColumn());		    		
			    	}
			    	
					break;
				default:
					break;
				}
				
				break;
			case Sensor:
				switch(e.getSensor().getType()) {
				case Light:
					SensorVO ls = sensorContainer.getSensors().stream().filter(sen -> sen.getId() == e.getSensor().getId()).findFirst().get();
			    	LightWidget lw;
			    	if (ls instanceof LightSensorVO) {
			    		lw = new LightWidget(e.getId(), (LightSensorVO) ls, application, this);
			    	} else {
			    		lw = new LightWidget(e.getId(), (DummyLightSensorVO) ls, application, this);
			    	}
			    	
			    	System.out.println(lw.getPanelId());
			    	
			    	if (!widgets.containsKey(lw.getPanelId())) {
			    		toRender.put(lw, e.getColumn());		    		
			    	}
			    	
					break;
				case Temperature:
					SensorVO ts = sensorContainer.getSensors().stream().filter(sen -> sen.getId() == e.getSensor().getId()).findFirst().get();
					
			    	TemperatureWidget tw;
			    	if (ts instanceof TemperatureSensorVO) {
			    		tw = new TemperatureWidget(e.getId(), (TemperatureSensorVO) ts, application, this);
			    	} else {
			    		tw = new TemperatureWidget(e.getId(), (DummyTemperatureSensorVO) ts, application, this);
			    	}
			    	
			    	System.out.println(tw.getPanelId());
			    	
			    	if (!widgets.containsKey(tw.getPanelId())) {
			    		toRender.put(tw, e.getColumn());		    		
			    	}
			    	
					break;
				default:
					break;
				}
				
				break;
			default:
				break;
    		}
    	}
		

		if (!FacesContext.getCurrentInstance().isPostback()) {
	    	dashboard.getChildren().removeIf(c -> c.getClass().equals(Panel.class));
	    	dashboardModel.getColumns().forEach(c -> c.getWidgets().clear());
			widgets.clear();
		}
		
		System.out.println("LoadLayout render");
		for (IndexWidget w : toRender.keySet()) {
			System.out.println(w.getPanelId() + ": " + toRender.get(w) + ", " + dashboardModel.getColumn(toRender.get(w)).getWidgetCount());
			widgets.put(w.getPanelId(), w);
			dashboard.getChildren().add(w.getAsPanel());
    		dashboardModel.getColumn(toRender.get(w)).addWidget(w.getPanelId());
		}
		
    }
    
    
    public void saveLayout() {
    	System.out.println("SaveLayout");
    	List<OverviewLayoutElementVO> elements = new ArrayList<>();
    	for (int colNum = 0; colNum < dashboardModel.getColumnCount(); ++colNum) {
    		DashboardColumn col = dashboardModel.getColumn(colNum);
    		for (int rowNum = 0; rowNum < col.getWidgetCount(); ++rowNum) {
    			IndexWidget widget = widgets.get(col.getWidget(rowNum)); 
    			
    			System.out.println(widget.getPanelId() + " (col,row): " + colNum + ", " + rowNum);
    			
    			OverviewLayoutElementVO newElement = new OverviewLayoutElementVO();
    			newElement.setId(widget.getId());
    			newElement.setType(OverviewLayoutElementType.valueOf(widget.getType()));
    			newElement.setSensor(widget.getSensor());
    			newElement.setActor(widget.getActor());
    			newElement.setColumn(colNum);
    			newElement.setRow(rowNum);
    			elements.add(newElement);
    		}
    	}
    	selectedLayout.setElements(elements);
    	layoutContainer.updateLayout(selectedLayout);
    	setLayouts(layoutContainer.getLayouts());
    }
    
    public void newLayout() {
    	
    	OverviewLayoutVO layout = new OverviewLayoutVO();
    	layout.setId(0l);
    	layout.setName(newLayoutName);
    	layout.setElements(new ArrayList<OverviewLayoutElementVO>());
    	layoutContainer.addLayout(layout);
    	setLayouts(layoutContainer.getLayouts());
    	newLayoutName = "";
    }
    
    public void renameLayout () {
    	selectedLayout.setName(renameLayoutName);
    	layoutContainer.updateLayout(selectedLayout);
    	setLayouts(layoutContainer.getLayouts());
    	renameLayoutName = "";
    }
    
    public void removeLayout () {
    	layoutContainer.deleteLayout(selectedLayout.getId());
    	setLayouts(layoutContainer.getLayouts());
    	
    	if (layoutContainer.getLayouts().size() < 1) {
			newLayoutName = "Default layout";
			newLayout();
			newLayoutName = "";
		}
    	selectedLayout = layouts.get(0);	
    	loadLayout();
    }
    
    public void layoutChanged() {
    	dashboard.getChildren().removeIf(c -> c.getClass().equals(Panel.class));
    	dashboardModel.getColumns().forEach(c -> c.getWidgets().clear());
		widgets.clear();
    	loadLayout();
    }
    
    public void closedWidget(String clientId) {
    	dashboardModel.getColumns().forEach(col -> col.removeWidget(clientId));
    	dashboard.getChildren().removeIf(c -> c.getClientId().equals(clientId));
    	selectedLayout.getElements().removeIf(e -> e.getId().longValue() == widgets.get(clientId).getId().longValue());
    	widgets.remove(clientId);
    	saveLayout();
    }
    
    public void newSensorWidget(String id) {
    	SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == Long.parseLong(id)).findFirst().get();
    	
    	OverviewLayoutElementVO e = new OverviewLayoutElementVO();
    	e.setId(0l);
    	e.setType(OverviewLayoutElementType.Sensor);
    	e.setSensor(sensor);
    	e.setActor(null);
    	e.setColumn(0);
    	e.setRow(0);
    	
    	selectedLayout.getElements().add(e);
    	layoutContainer.updateLayout(selectedLayout);
    	selectedLayout = layoutContainer.getLayouts().stream().filter(l -> l.getId().longValue() == selectedLayout.getId().longValue()).findFirst().get();
    	loadLayout();
    	
    }
    
    public void newActorWidget(String id) {
    	ActorVO actor = actorContainer.getActors().stream().filter(a -> a.getId() == Long.parseLong(id)).findFirst().get();
    	
    	OverviewLayoutElementVO e = new OverviewLayoutElementVO();
    	e.setId(0l);
    	e.setType(OverviewLayoutElementType.Actor);
    	e.setSensor(null);
    	e.setActor(actor);
    	e.setColumn(0);
    	e.setRow(0);
    	
    	selectedLayout.getElements().add(e);
    	layoutContainer.updateLayout(selectedLayout);
    	selectedLayout = layoutContainer.getLayouts().stream().filter(l -> l.getId().longValue() == selectedLayout.getId().longValue()).findFirst().get();
    	loadLayout();
    }
    
    public List<SensorVO> getSensorByType(String type) {
    	return sensorContainer.getSensors().stream().filter(s -> s.getType() == SensorType.valueOf(type)).collect(Collectors.toList());
    }
    
    public List<ActorVO> getActorByType(String type) {
    	return actorContainer.getActors().stream().filter(a -> a.getType() == ActorType.valueOf(type)).collect(Collectors.toList());
    }
    
    public void refresh () {
    	for (IndexWidget w : widgets.values()) {
    		w.refresh();
    	}
    }
    
    public void handleReorder(DashboardReorderEvent event) {
    	saveLayout();
    }
    

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
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

	public String getRenameLayoutName() {
		return renameLayoutName;
	}

	public void setRenameLayoutName(String renameLayoutName) {
		this.renameLayoutName = renameLayoutName;
	}
    
    

}
