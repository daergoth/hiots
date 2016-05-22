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
import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;

public class LightWidget implements IndexWidget {
	
private static final List<Number> intervals = Arrays.asList( 200, 800, 1000);
	
	private Long id;
	
	private String type;
	
	private Application application;

	private SensorVO lightSensor;
	
	private MeterGaugeChartModel gaugeModel;
	
	private IndexManager manager;

	public LightWidget(Long id, LightSensorVO sensor, Application application, IndexManager manager) {
		this.id = id;
		this.type = "Sensor";
		this.lightSensor = sensor;
		this.application = application;
		this.manager = manager;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("00396B,81e741,f2ee00");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(0);
    	gaugeModel.setMax(1000);
    	
    	gaugeModel.setTitle(lightSensor.getName());
    	gaugeModel.setValue(lightSensor.getData().getData());
    	gaugeModel.setGaugeLabel(lightSensor.getData().toString());
	}
	
	public LightWidget(Long id, DummyLightSensorVO sensor, Application application, IndexManager manager) {
		this.id = id;
		this.type = "Sensor";
		this.lightSensor = sensor;
		this.application = application;
		this.manager = manager;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("00396B,81e741,f2ee00");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(0);
    	gaugeModel.setMax(1000);
    	
    	gaugeModel.setTitle(lightSensor.getName());
    	gaugeModel.setValue(lightSensor.getData().getData());
    	gaugeModel.setGaugeLabel(lightSensor.getData().toString());
	}

	@Override
	public void refresh() {
		gaugeModel.setValue(lightSensor.getData().getData());
    	gaugeModel.setGaugeLabel(lightSensor.getData().toString());
	}

	@Override
	public Panel getAsPanel() {
		Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		p.setId(getPanelId());
		p.setHeader("Light widget");
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

	@Override
	public String getPanelId() {
		return "lightWidget_" + id;
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
		return lightSensor;
	}

	@Override
	public ActorVO getActor() {
		return null;
	}

	
	
	

}
