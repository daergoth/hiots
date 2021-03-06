package net.daergoth.web.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.daergoth.serviceapi.sensors.SensorContainerLocal;
import net.daergoth.serviceapi.sensors.SensorVO;

@ManagedBean(name = "sensorConverter")
@RequestScoped
public class SensorConverter implements Converter {
	
	@EJB
	private SensorContainerLocal sensorContainer;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {            	
                return sensorContainer.getSensors().stream().filter(s -> s.getId().equals(Long.parseLong(value))).findFirst().get();
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid id."));
            } 
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((SensorVO)object).getId());
		} else {
			return null;
		}
	}

}
