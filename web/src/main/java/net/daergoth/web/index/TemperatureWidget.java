package net.daergoth.web.index;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.chart.Chart;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.chart.MeterGaugeChartModel;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

public class TemperatureWidget implements IndexWidget {

	private static final List<Number> intervals = Arrays.asList(-3, 4, 32, 46, 60);
	
	private Long id;

	private String type;
	
	private Application application;

	private SensorVO tempSensor;
	
	private MeterGaugeChartModel gaugeModel;
	
	private IndexManager manager;
	
	public TemperatureWidget(Long id, TemperatureSensorVO tempSensor, Application application, IndexManager manager) {
		this.id = id;
		this.type = "Sensor";
		this.tempSensor = tempSensor;
		this.application = application;
		this.manager = manager;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("2c7bf6,97dafb,88c82a,edec5b,c43030");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(-10);
    	gaugeModel.setMax(60);
    	
    	gaugeModel.setTitle(tempSensor.getName());
    	gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	public TemperatureWidget(Long id, DummyTemperatureSensorVO tempSensor, Application application, IndexManager manager) {
		this.id  = id;
		this.type = "Sensor";
		this.tempSensor = tempSensor;
		this.application = application;
		this.manager = manager;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("2c7bf6,97dafb,88c82a,f2ee00,c43030");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(-10);
    	gaugeModel.setMax(60);
    	
    	gaugeModel.setTitle(tempSensor.getName());
    	gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	@Override
	public void refresh() {
		gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	@Override
	public Panel getAsPanel() {
		Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		p.setId(getPanelId());
		p.setHeader("Temperature widget");
		p.setClosable(true);
		p.setToggleable(false);
		p.setTransient(true);
		
		AjaxBehavior ajaxBehavior = new AjaxBehavior();
		ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				manager.closedWidget(event.getComponent().getId());
			}
		});
		ajaxBehavior.setTransient(true);
		p.addClientBehavior("close", ajaxBehavior);
		
		Chart g = (Chart) application.createComponent(FacesContext.getCurrentInstance(), 
				"org.primefaces.component.Chart", "org.primefaces.component.ChartRenderer");
		g.setType("metergauge");
		g.setStyle("width:100%;height:200px;");
		g.setResponsive(true);
		g.setModel(gaugeModel);
		p.getChildren().add(g);
		
		return p;
	}
	
	public String getPanelId() {
		return "tempWidget_" + id;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public SensorVO getSensor() {
		return tempSensor;
	}

	@Override
	public ActorVO getActor() {
		return null;
	}

}
