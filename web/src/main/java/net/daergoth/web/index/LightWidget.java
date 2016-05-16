package net.daergoth.web.index;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.chart.MeterGaugeChartModel;

import net.daergoth.serviceapi.sensors.LightSensorVO;
import net.daergoth.serviceapi.sensors.SensorVO;
import net.daergoth.serviceapi.sensors.dummy.DummyLightSensorVO;

public class LightWidget implements IndexWidget {
	
private static final List<Number> intervals = Arrays.asList( 200, 800, 1000);
	
	private Long id;
	
	private Application application;

	private SensorVO lightSensor;
	
	private MeterGaugeChartModel gaugeModel;

	public LightWidget(LightSensorVO sensor, Application application) {
		this.lightSensor = sensor;
		this.application = application;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("00396B,A1FF66,FFFB00");
		gaugeModel.setGaugeLabelPosition("bottom");
		gaugeModel.setShowTickLabels(true);
		gaugeModel.setLabelHeightAdjust(0);
		gaugeModel.setMin(0);
    	gaugeModel.setMax(1000);
    	
    	gaugeModel.setTitle(lightSensor.getName());
    	gaugeModel.setValue(lightSensor.getData().getData());
    	gaugeModel.setGaugeLabel(lightSensor.getData().toString());
	}
	
	public LightWidget(DummyLightSensorVO sensor, Application application) {
		this.lightSensor = sensor;
		this.application = application;
		
		gaugeModel = new MeterGaugeChartModel(null, intervals);
		gaugeModel.setSeriesColors("00396B,A1FF66,FFFB00");
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
		p.setId("tempWidget_" + id);
		p.setHeader("Light widget");
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

	@Override
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
