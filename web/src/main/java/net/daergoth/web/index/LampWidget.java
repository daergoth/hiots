package net.daergoth.web.index;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.panel.Panel;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public class LampWidget implements IndexWidget {

	private Long id;
	
	private String type;
	
	private Application application;
	
	private ActorVO lampActor;
	
	private IndexManager manager;
	
	public LampWidget(Long id, LampActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.lampActor = actor;
		this.application = app;
		this.manager = manager;
	}
	
	public LampWidget(Long id, DummyLampActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.lampActor = actor;
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
		p.setHeader("Lamp widget");
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
		return "lampWidget_" + id;
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
		return lampActor;
	}

	@Override
	public SensorVO getSensor() {
		return null;
	}

	
	

}
