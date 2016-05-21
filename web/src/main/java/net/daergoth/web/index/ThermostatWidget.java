package net.daergoth.web.index;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.panel.Panel;
import org.primefaces.extensions.component.knob.Knob;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public class ThermostatWidget implements IndexWidget {
	
	private Long id;
	
	private String type;
	
	private Application application;
	
	private ActorVO thermostatActor;
	
	private IndexManager manager;
	
	private Knob knob;
	
	public ThermostatWidget(Long id, ThermostatActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.thermostatActor = actor;
		this.application = app;
		this.manager = manager;
	}
	
	public ThermostatWidget(Long id, DummyThermostatActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.thermostatActor = actor;
		this.application = app;
		this.manager = manager;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public Panel getAsPanel() {
		Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		p.setId(getPanelId());
		p.setHeader("Thermostat widget");
		p.setClosable(true);
		p.setToggleable(false);
		
		AjaxBehavior ajaxBehavior = new AjaxBehavior();
		ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				manager.closedWidget(event.getComponent().getId());
			}
		});
		ajaxBehavior.setTransient(true);
		p.addClientBehavior("close", ajaxBehavior);
		
		return p;
	}

	@Override
	public String getPanelId() {
		return "thermostatWidget_" + id;
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
	public ActorVO getActor() {
		return thermostatActor;
	}

	@Override
	public SensorVO getSensor() {
		return null;
	}
	
	

}
