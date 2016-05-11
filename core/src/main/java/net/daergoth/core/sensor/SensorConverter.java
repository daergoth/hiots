package net.daergoth.core.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.DummySensorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

public class SensorConverter {
	
	public static SensorDTO toDTO(Sensor s) {
		if (s.getDummyInfo() != null) {
			DummySensorDTO dto = new DummySensorDTO();
			dto.setId(s.getId());
			dto.setName(s.getName());
			dto.setType(s.getType());
			dto.setMin(s.getDummyInfo().getMinData());
			dto.setMax(s.getDummyInfo().getMaxData());
			dto.setInterval(s.getDummyInfo().getRefreshInterval());
			return dto;
		} else {
			SensorDTO dto = new SensorDTO();
			dto.setId(s.getId());
			dto.setName(s.getName());
			dto.setType(s.getType());
			return dto;
		}
		
	}
	
	public static List<SensorDTO> toDTOs(List<Sensor> list) {
		List<SensorDTO> dto_list = new ArrayList<>();
		
		for (Sensor entity : list) {
			dto_list.add(SensorConverter.toDTO(entity));
		}
		
		return dto_list;
	}
	
	public static Sensor toEntity(SensorDTO s) {
		if (s.getClass().equals(DummySensorDTO.class)) {
			DummySensorDTO ds = (DummySensorDTO) s;
			
			Sensor e = new Sensor();
			e.setId(ds.getId());
			e.setName(ds.getName());
			e.setType(ds.getType());
			
			DummySensorInformation info = new DummySensorInformation();
			info.setId(ds.getId());
			info.setMinData(ds.getMin());
			info.setMaxData(ds.getMax());
			info.setRefreshInterval(ds.getInterval());
			e.setDummyInfo(info);
			
			return e;
		} else {
			Sensor e = new Sensor();
			e.setId(s.getId());
			e.setName(s.getName());
			e.setType(s.getType());
			
			return e;
		}
	}
	
	public static List<Sensor> toEntities(List<SensorDTO> list) {
		List<Sensor> entities = new ArrayList<>();
		
		for (SensorDTO dto : list) {
			entities.add(SensorConverter.toEntity(dto));
		}
		
		return entities;
	}
	
}
