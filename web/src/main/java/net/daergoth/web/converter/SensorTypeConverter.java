package net.daergoth.web.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import net.daergoth.serviceapi.sensors.SensorType;

@ManagedBean(name = "sensTypeConverter")
@RequestScoped
public class SensorTypeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().length() > 0) {
            return SensorType.valueOf(value);
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			return ((SensorType) object).toString();
		} else {
			return null;
		} 
	}

}
