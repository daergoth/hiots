package net.daergoth.core;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.DummySensorDTO;
import net.daergoth.coreapi.SensorDTO;

public class SensorConverter {
	
	public static SensorDTO toSensorDTO(Sensor s) {
		SensorDTO dto = new SensorDTO();
		dto.setId(s.getId());
		dto.setName(s.getName());
		dto.setType(s.getType());
		return dto;
	}
	
	public static List<SensorDTO> toSensorDTOs(List<Sensor> list) {
		List<SensorDTO> dto_list = new ArrayList<>();
		
		for (Sensor entity : list) {
			dto_list.add(SensorConverter.toSensorDTO(entity));
		}
		
		return dto_list;
	}
	
	public static DummySensorDTO toDummyDTO(Sensor s) {
		DummySensorDTO dto = new DummySensorDTO();
		dto.setId(s.getId());
		dto.setName(s.getName());
		dto.setType(s.getType());
		dto.setMin(s.getDummyInfo().getMinData());
		dto.setMax(s.getDummyInfo().getMaxData());
		dto.setInterval(s.getDummyInfo().getRefreshInterval());
		return dto;
	}
	
	public static List<DummySensorDTO> toDummySensorDTOs(List<Sensor> list) {
		List<DummySensorDTO> dto_list = new ArrayList<>();
		
		for (Sensor entity : list) {
			dto_list.add(SensorConverter.toDummyDTO(entity));
		}
		
		return dto_list;
	}
	
	public static Sensor toEntity(DummySensorDTO s) {
		
		
		Sensor e = new Sensor();
		e.setId(s.getId());
		e.setName(s.getName());
		e.setType(s.getType());
		
		DummySensorInformation info = new DummySensorInformation();
		info.setSensorId(s.getId());
		info.setMinData(s.getMin());
		info.setMaxData(s.getMax());
		info.setRefreshInterval(s.getInterval());
		e.setDummyInfo(info);
		
		return e;
	}
	
	public static List<Sensor> toEntities(List<DummySensorDTO> list) {
		List<Sensor> entities = new ArrayList<>();
		
		for (DummySensorDTO dto : list) {
			entities.add(SensorConverter.toEntity(dto));
		}
		
		return entities;
	}
	
	public static Sensor toEntity(SensorDTO s) {
		
		if (s.getClass().equals(DummySensorDTO.class)) {
			DummySensorDTO ds = (DummySensorDTO) s;
			
			Sensor e = new Sensor();
			e.setId(ds.getId());
			e.setName(ds.getName());
			e.setType(ds.getType());
			
			DummySensorInformation info = new DummySensorInformation();
			info.setSensorId(ds.getId());
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
	
	/*
	public static List<Sensor> toEntities(List<SensorDTO> list) {
		List<Sensor> entities = new ArrayList<>();
		
		for (SensorDTO dto : list) {
			entities.add(SensorConverter.toEntity(dto));
		}
		
		return entities;
	}
	*/
}
