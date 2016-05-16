package net.daergoth.web.converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import net.daergoth.serviceapi.monitor.OverviewLayoutContainerLocal;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;

@ManagedBean(name = "overviewLayoutConverter")
@RequestScoped
public class OverviewLayoutConverter implements Converter {
	
	@EJB
	OverviewLayoutContainerLocal layoutContainer;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().length() > 0) {
            try {            	
                return layoutContainer.getLayouts().stream().filter(a -> a.getId().equals(Long.parseLong(value))).findFirst().get();
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
		if (object != null && object != "") {
			return String.valueOf(((OverviewLayoutVO)object).getId());
		} else {
			return null;
		}
	}

}
