package net.daergoth.serviceapi;

import javax.faces.context.FacesContext;

import net.daergoth.serviceapi.sensors.datatypes.SensorData;

public interface DataChangeHandler {
	
	public DataChangeHandler setFacesContext(FacesContext ctx);

	public void onChange(SensorData newData);
	
}
