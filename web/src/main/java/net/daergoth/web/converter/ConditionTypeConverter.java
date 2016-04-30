package net.daergoth.web.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import net.daergoth.serviceapi.rule.ConditionTypeService;

@ManagedBean(name = "condTypeConverter")
@RequestScoped
public class ConditionTypeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().length() > 0) {
			switch (value) {
			case "=":
				return ConditionTypeService.EQ;
				//break;
			case ">=":
				return ConditionTypeService.GE;
				//break;
			case ">":
				return ConditionTypeService.GT;
				//break;
			case "<=":
				return ConditionTypeService.LE;
				//break;
			case "<":
				return ConditionTypeService.LT;
				//break;
			default:
				return null;
				//break;
			}
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			switch (((ConditionTypeService) object)) {
			case EQ:
				return "=";
				//break;
			case GE:
				return ">=";
				//break;
			case GT:
				return ">";
				//break;
			case LE:
				return "<=";
				//break;
			case LT:
				return "<";
				//break;
			default:
				return null;
				//break;
			}
		} else {
			return null;
		} 
	}

}
