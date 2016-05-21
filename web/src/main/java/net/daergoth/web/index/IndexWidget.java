package net.daergoth.web.index;

import org.primefaces.component.panel.Panel;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.sensors.SensorVO;

public interface IndexWidget {
	
	public void refresh();
	
	public Panel getAsPanel();
	
	public String getPanelId();
	
	public Long getId();

	public String getType();
	
	public SensorVO getSensor();
	
	public ActorVO getActor();
}
