package net.daergoth.web;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

@ManagedBean(name = "liveManager")
@ViewScoped
public class LiveManager {
	
	@EJB
	private SensorContainerLocal sensorContainer;
	
	@EJB
	private ActorContainerLocal actorContainer;

	private List<SensorVO> sensors;
	
	private List<SensorVO> selectedSensors;
	
	private List<ActorVO> actors;
	
	private LineChartModel liveChartModel;
	
	private HashMap<SensorVO, LineChartSeries> graphs;
	
	private DateAxis xAxis;

	@PostConstruct
	public void init() {
		setSensors(sensorContainer.getSensors()); 
		setActors(actorContainer.getActors());
		graphs = new HashMap<>();
		
		liveChartModel = new LineChartModel();
		liveChartModel.setTitle("Live Data");
		liveChartModel.setLegendPosition("e");
		
		xAxis = new DateAxis("Time");
		xAxis.setTickAngle(-55);
		xAxis.setTickCount(31);
		xAxis.setTickFormat("%H:%M:%S");
		
		liveChartModel.getAxes().put(AxisType.X, xAxis);
		
		selectedSensors = new ArrayList<>();
		selectedSensors.add(sensors.get(0));
		refresh();
	}
	
	public void refresh() {
		LocalTime absMax = LocalTime.MIN;
		LocalTime absMin = LocalTime.MAX;
		
		for (SensorVO sensor : selectedSensors) {
			if (!graphs.containsKey(sensor)) {
				LineChartSeries series = new LineChartSeries();
				graphs.put(sensor, series);
				series.setLabel(sensor.getName());
				liveChartModel.addSeries(series);
			}
			
			System.out.println("------" + LocalTime.now().toString() + " -> " + sensor.getData().getData());
			graphs.get(sensor).set(LocalTime.now().toString(), sensor.getData().getData());
			
			LocalTime min = graphs.get(sensor).getData().keySet().stream().map(o -> LocalTime.parse( (String)o )).min((t1, t2) -> t1.compareTo(t2)).get();
			
			if (graphs.get(sensor).getData().size() > 30) {
				graphs.get(sensor).getData().remove(min.toString());
				min = graphs.get(sensor).getData().keySet().stream().map(o -> LocalTime.parse( (String)o )).min((t1, t2) -> t1.compareTo(t2)).get();
			}
			
			LocalTime max = graphs.get(sensor).getData().keySet().stream().map(o -> LocalTime.parse( (String)o )).min((t1, t2) -> t2.compareTo(t1)).get().plusSeconds(2);
			
			if (max.compareTo(absMax) > 0) {
				absMax = max;
			}
			
			if (min.compareTo(absMin) < 0) {
				absMin = min;
			}
			
		}
		
		xAxis.setMin(absMin.toString());
		xAxis.setMax(absMax.toString());
		
	}
	
	public void forceRefresh() {
		setSensors(sensorContainer.getSensors());
	}
 
    public void onRowUnselect(UnselectEvent event) {
    	List<Map<Object, Number> > save = new ArrayList<>();
    	for (SensorVO s : selectedSensors) {
    		save.add(graphs.get(s).getData());
    	}
    	
    	graphs.clear();
    	liveChartModel.clear();
    	
    	for (int i = 0; i < selectedSensors.size(); ++i) {
    		LineChartSeries series = new LineChartSeries();
			graphs.put(selectedSensors.get(i), series);
			series.setLabel(selectedSensors.get(i).getName());
			series.setData(save.get(i));
			liveChartModel.addSeries(series);
    	}
    }

	public List<SensorVO> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorVO> sensors) {
		this.sensors = sensors;
	}

	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}

	public LineChartModel getLiveChartModel() {
		return liveChartModel;
	}

	public void setLiveChartModel(LineChartModel liveChartModel) {
		this.liveChartModel = liveChartModel;
	}

	public List<SensorVO> getSelectedSensors() {
		return selectedSensors;
	}

	public void setSelectedSensors(List<SensorVO> selectedSensors) {
		this.selectedSensors = selectedSensors;
	}
	
}
