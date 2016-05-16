package net.daergoth.web.index;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.chart.MeterGaugeChartModel;

import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.TemperatureSensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyTemperatureSensorVO;

public class TemperatureWidget implements IndexWidget {

	private static final List<Number> intervals = Arrays.asList(-3, 4, 32, 46, 60);
	
	private Long id;
	
	private Application application;

	private SensorVO tempSensor;
	
	private MeterGaugeChartModel gaugeModel;
	
	public TemperatureWidget(TemperatureSensorVO tempSensor, Application application) {
		this.tempSensor = tempSensor;
		this.application = application;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("4D8FF7,6CCEFF,93b75f,E7E658,cc6666");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(-10);
    	gaugeModel.setMax(60);
    	
    	gaugeModel.setTitle(tempSensor.getName());
    	gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	public TemperatureWidget(DummyTemperatureSensorVO tempSensor, Application application) {
		this.tempSensor = tempSensor;
		this.application = application;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("4D8FF7,6CCEFF,93b75f,E7E658,cc6666");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(-10);
    	gaugeModel.setMax(60);
    	
    	gaugeModel.setTitle(tempSensor.getName());
    	gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	public void refresh() {
		gaugeModel.setValue(tempSensor.getData().getData());
    	gaugeModel.setGaugeLabel(tempSensor.getData().toString());
	}
	
	public Panel getAsPanel() {
		Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		p.setId("tempWidget_" + id);
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
	
	public String getPanelId() {
		return "tempWidget_" + id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
