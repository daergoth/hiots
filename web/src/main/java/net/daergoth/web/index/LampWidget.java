package net.daergoth.web.index;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyLampActorVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public class LampWidget implements IndexWidget {

	private Long id;
	
	private String type;
	
	private Application application;
	
	private ActorVO lampActor;
	
	private IndexManager manager;
	
	SelectBooleanButton button;
	
	public LampWidget(Long id, LampActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.lampActor = actor;
		this.application = app;
		this.manager = manager;
		
		button = (SelectBooleanButton) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.SelectBooleanButton", "org.primefaces.component.SelectBooleanButtonRenderer");
		
		button.setOnLabel("On");
		button.setOffLabel("Off");
		button.setStyle("width: 175px; height: 75px; font-size: 40px; margin: auto; display: block;");
		button.setValue( lampActor.getState().getData() == 1.0 );
		AjaxBehavior ajaxBehavior = new AjaxBehavior();
		ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				try {
					LampActorStateVO state = new LampActorStateVO();
					state.setStatus(lampActor.getState().getData() != 1.0);
					lampActor.setState(state);
				} catch (InvalidActorStateTypeException e) {
					e.printStackTrace();
				}
			}
		});
		ajaxBehavior.setTransient(true);
		button.addClientBehavior("change", ajaxBehavior);
	}
	
	public LampWidget(Long id, DummyLampActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.lampActor = actor;
		this.application = app;
		this.manager = manager;
		
		button = (SelectBooleanButton) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.SelectBooleanButton", "org.primefaces.component.SelectBooleanButtonRenderer");
		
		button.setOnLabel("On");
		button.setOffLabel("Off");
		button.setStyle("width: 175px; height: 75px; font-size: 40px; margin: auto;  display: block;");
		button.setValue( lampActor.getState().getData() == 1.0 );
		AjaxBehavior ajaxBehavior = new AjaxBehavior();
		ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				try {
					LampActorStateVO state = new LampActorStateVO();
					state.setStatus(lampActor.getState().getData() != 1.0);
					lampActor.setState(state);
				} catch (InvalidActorStateTypeException e) {
					e.printStackTrace();
				}
			}
		});
		ajaxBehavior.setTransient(true);
		button.addClientBehavior("change", ajaxBehavior);
	}
	
	@Override
	public void refresh() {
		button.setValue( ((LampActorStateVO) lampActor.getState()).getStatus() );
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
		
		HtmlOutputText title = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		title.setValue(lampActor.getName());
		title.setStyle("color: #666; font-size: 25px; margin: 10px 0; display: block; text-align: center;");
		
		p.getChildren().add(title);
		p.getChildren().add(button);
		
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
