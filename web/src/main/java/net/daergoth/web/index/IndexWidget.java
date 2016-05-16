package net.daergoth.web.index;

import org.primefaces.component.panel.Panel;

public interface IndexWidget {
	
	public void refresh();
	
	public Panel getAsPanel();
	
	public String getPanelId();
	
}
