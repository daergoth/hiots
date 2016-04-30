package net.daergoth.web.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.daergoth.serviceapi.actors.ActorContainerLocal;
import net.daergoth.serviceapi.actors.ActorVO;

@ManagedBean(name = "actorConverter")
@RequestScoped
public class ActorConverter implements Converter {
	
	@EJB
	ActorContainerLocal actorContainer;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().length() > 0) {
            try {            	
                return actorContainer.getActors().stream().filter(a -> a.getId().equals(Long.parseLong(value))).findFirst().get();
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid id."));
            } 
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ActorVO)object).getId());
		} else {
			return null;
		}
	}

}
