package net.daergoth.web.index;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.slider.Slider;
import org.primefaces.event.SlideEndEvent;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.InvalidActorStateTypeException;
import net.daergoth.serviceapi.actors.ThermostatActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyThermostatActorVO;
import net.daergoth.serviceapi.actors.states.ThermostatActorStateVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public class ThermostatWidget implements IndexWidget {
	
	private Long id;
	
	private String type;
	
	private Application application;
	
	private ActorVO thermostatActor;
	
	private IndexManager manager;
	
	private InputText input;
	
	public ThermostatWidget(Long id, ThermostatActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.thermostatActor = actor;
		this.application = app;
		this.manager = manager;
		
		input = (InputText) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.InputText", "org.primefaces.component.InputTextRenderer");
		input.setValue(thermostatActor.getState().getData());
		input.setReadonly(true);
		input.setStyle("width: 100%; text-align: center; height: 75px; font-size: 40px;");
	}
	
	public ThermostatWidget(Long id, DummyThermostatActorVO actor, Application app, IndexManager manager) {
		this.id = id;
		this.type = "Actor";
		this.thermostatActor = actor;
		this.application = app;
		this.manager = manager;
		
		input = (InputText) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.InputText", "org.primefaces.component.InputTextRenderer");
		input.setValue(thermostatActor.getState().getData());
		input.setReadonly(true);
		input.setStyle("width: 100%; text-align: center; height: 75px; font-size: 40px;");
	}

	@Override
	public void refresh() {
		input.setValue(thermostatActor.getState().getData());
	}

	@Override
	public Panel getAsPanel() {
		Panel p = (Panel) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
		p.setId(getPanelId());
		p.setHeader("Thermostat widget");
		p.setClosable(true);
		p.setToggleable(false);
		
		AjaxBehavior ajaxBehaviorPanel = new AjaxBehavior();
		ajaxBehaviorPanel.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				manager.closedWidget(event.getComponent().getId());
			}
		});
		ajaxBehaviorPanel.setTransient(true);
		p.addClientBehavior("close", ajaxBehaviorPanel);
		
		HtmlOutputText title = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
		title.setValue(thermostatActor.getName());
		title.setStyle("color: #666; font-size: 25px; margin: 10px 0; display: block; text-align: center;");
		
		Slider slider = (Slider) application.createComponent(FacesContext.getCurrentInstance(),
				"org.primefaces.component.Slider", "org.primefaces.component.SliderRenderer");
		slider.setFor(input.getClientId());
		slider.setMinValue(0);
		slider.setMaxValue(35);
		
		AjaxBehavior ajaxBehaviorSlider = new AjaxBehavior();
		ajaxBehaviorSlider.addAjaxBehaviorListener(new AjaxBehaviorListener() {
			
			@Override
			public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
				ThermostatActorStateVO thermoState = new ThermostatActorStateVO();
				thermoState.setData( (double) ((SlideEndEvent)event).getValue() );
				try {
					thermostatActor.setState(thermoState);
				} catch (InvalidActorStateTypeException e) {}
			}
		});
		ajaxBehaviorSlider.setTransient(true);
		slider.addClientBehavior("slideEnd", ajaxBehaviorSlider);
		
		p.getChildren().add(title);
		p.getChildren().add(input);
		p.getChildren().add(slider);
		
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
