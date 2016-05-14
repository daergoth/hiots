package net.daergoth.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.MeterGaugeChartModel;

import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

@ManagedBean(name = "indexManager")
@ViewScoped
public class IndexManager {
	
	private static final int DASHBOARD_COLUMN_NUM = 3;
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	private static Application application;
	
	private Dashboard dashboard;
	
	private DefaultDashboardModel dashboardModel;
	
	private List<SensorVO> tempSensors;
	
	private static class TemperatureWidget {
		
		private static final List<Number> intervals = Arrays.asList(-3, 4, 32, 46, 60);

		private SensorVO tempSensor;
		
		private MeterGaugeChartModel gaugeModel;
		
		public TemperatureWidget(TemperatureSensorVO tempSensor) {
			this.tempSensor = tempSensor;
			gaugeModel = new MeterGaugeChartModel(null, intervals);
			gaugeModel.setSeriesColors("4D8FF7,6CCEFF,93b75f,E7E658,cc6666");
			gaugeModel.setGaugeLabelPosition("bottom");
			gaugeModel.setShowTickLabels(true);
			gaugeModel.setLabelHeightAdjust(0);
			gaugeModel.setMin(-10);
	    	gaugeModel.setMax(60);
	    	
	    	gaugeModel.setTitle(tempSensor.getName());
	    	gaugeModel.setValue(tempSensor.getData().getData());
	    	gaugeModel.setGaugeLabel(gaugeModel.getValue() + "°C");
		}
		
		public TemperatureWidget(DummyTemperatureSensorVO tempSensor) {
			this.tempSensor = tempSensor;
			gaugeModel = new MeterGaugeChartModel(null, intervals);
			gaugeModel.setSeriesColors("4D8FF7,6CCEFF,93b75f,E7E658,cc6666");
			gaugeModel.setGaugeLabelPosition("bottom");
			gaugeModel.setShowTickLabels(true);
			gaugeModel.setLabelHeightAdjust(0);
			gaugeModel.setMin(-10);
	    	gaugeModel.setMax(60);
	    	
	    	gaugeModel.setTitle(tempSensor.getName());
	    	gaugeModel.setValue(tempSensor.getData().getData());
	    	gaugeModel.setGaugeLabel(gaugeModel.getValue() + "°C");
		}
		
		public void refresh() {
			gaugeModel.setValue(tempSensor.getData().getData());
	    	gaugeModel.setGaugeLabel(gaugeModel.getValue() + "°C");
		}
		
		public Panel getAsPanel() {
			Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
					"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
			p.setId("tempWidget_" + tempSensor.getId());
			p.setHeader("Temperature widget");
			p.setClosable(true);
			p.setToggleable(false);
			
			Chart g = (Chart) application.createComponent(FacesContext.getCurrentInstance(), 
					"org.primefaces.component.Chart", "org.primefaces.component.ChartRenderer");
			g.setType("metergauge");
			g.setStyle("width:100%;height:200px;");
			g.setResponsive(true);
			g.setModel(gaugeModel);
			p.getChildren().add(g);
			
			return p;
		}
		
		public String getId() {
			return "tempWidget_" + tempSensor.getId();
		}
	}
    
    @PostConstruct
    public void init() {
		application = FacesContext.getCurrentInstance().getApplication();
		
		dashboard = (Dashboard) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
    	
		dashboardModel = new DefaultDashboardModel();
		
		for (int i = 0; i < DASHBOARD_COLUMN_NUM; ++i) {
			dashboardModel.addColumn(new DefaultDashboardColumn());
		}
		dashboard.setModel(dashboardModel);
        
        TemperatureWidget tw = new TemperatureWidget((DummyTemperatureSensorVO) sensorContainer.getDummySensors().stream()
        		.filter(s -> s.getType() == SensorType.Temperature)
        		.findFirst()
        		.get());
        
        dashboard.getChildren().add(tw.getAsPanel());
        dashboardModel.getColumn(0).addWidget(tw.getId());
        
        tempSensors = sensorContainer.getSensors().stream().filter(s -> s.getType() == SensorType.Temperature).collect(Collectors.toList());
    }
    
    public void newTemperatureWidget(String id) {
    	SensorVO sensor = sensorContainer.getSensors().stream().filter(s -> s.getId() == Long.parseLong(id)).findFirst().get();
    	TemperatureWidget tw;
    	if (sensor instanceof TemperatureSensorVO) {
    		tw = new TemperatureWidget((TemperatureSensorVO) sensor);
    	} else {
    		tw = new TemperatureWidget((DummyTemperatureSensorVO) sensor);
    	}
    	
    	dashboard.getChildren().add(tw.getAsPanel());
    	dashboardModel.getColumn(1).addWidget(tw.getId());
    }
    
    public void refresh () {
    	
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
    
    

}
