package net.daergoth.web.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import net.daergoth.serviceapi.sensors.SensorType;
import net.daergoth.serviceapi.sensors.datatypes.LightDataVO;
import net.daergoth.serviceapi.sensors.datatypes.SensorDataVO;
import net.daergoth.serviceapi.sensors.datatypes.TemperatureDataVO;

@ManagedBean(name = "sensorDataConverter")
@RequestScoped
public class SensorDataConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().length() > 0) {
            for (SensorType type : SensorType.values()) {
            	if (value.startsWith(type.toString())) {
            		value = value.split(": ")[1];
            		switch(type) {
					case Light:
						return new LightDataVO(Double.parseDouble(value.substring(0,value.indexOf(LightDataVO.UNIT))));
						//break;
					case Temperature:
						return new TemperatureDataVO(Double.parseDouble(value.substring(0,value.indexOf(TemperatureDataVO.UNIT))));
						//break;
					default:
						return null;
						//break;
            		}
            	}
            }
            return null;
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		SensorDataVO data = (SensorDataVO) object; 
		return data.getType().toString() + ": " + data.toString();
	}

}
