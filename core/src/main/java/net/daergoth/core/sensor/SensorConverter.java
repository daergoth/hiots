package net.daergoth.core.sensor;

import java.util.ArrayList;
import java.util.List;

import net.daergoth.coreapi.sensor.DummySensorDTO;
import net.daergoth.coreapi.sensor.SensorDTO;

/**
 * Converter between {@code Sensor} entity and {@code SensorDTO}.
 * Methods of this class should only be used in the Core layer.
 * 
 *  @see net.daergoth.core.sensor.Sensor
 *  @see net.daergoth.coreapi.sensor.SensorDTO
 */
public class SensorConverter {
	
	/**
	 * Converts a {@code Sensor} entity to DTO.
	 * @param entity the entity to convert to DTO
	 * @return the {@code SensorDTO} equivalent to the entity 
	 */
	public static SensorDTO toDTO(Sensor entity) {
		if (entity.getDummyInfo() != null) {
			DummySensorDTO dto = new DummySensorDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setType(entity.getType());
			dto.setMin(entity.getDummyInfo().getMinData());
			dto.setMax(entity.getDummyInfo().getMaxData());
			dto.setInterval(entity.getDummyInfo().getRefreshInterval());
			return dto;
		} else {
			SensorDTO dto = new SensorDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setType(entity.getType());
			return dto;
		}
		
	}
	
	/**
	 * Converts a list of {@code Sensor} entities to a list of DTOs.
	 * @param entityList list of entities to convert 
	 * @return list of {@code SensorDTO}s equivalent to the entities
	 */
	public static List<SensorDTO> toDTOs(List<Sensor> entityList) {
		List<SensorDTO> dto_list = new ArrayList<>();
		
		for (Sensor entity : entityList) {
			dto_list.add(SensorConverter.toDTO(entity));
		}
		
		return dto_list;
	}
	
	/**
	 * Converts a {@code SensorDTO} to entity .
	 * @param dto the DTO to convert to entity
	 * @return {@code Sensor} entity equivalent to the DTO
	 */
	public static Sensor toEntity(SensorDTO dto) {
		if (dto.getClass().equals(DummySensorDTO.class)) {
			DummySensorDTO ds = (DummySensorDTO) dto;
			
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
			e.setId(dto.getId());
			e.setName(dto.getName());
			e.setType(dto.getType());
			
			return e;
		}
	}
	
	/**
	 * Converts a list of {@code SensorDTO}s to a list of entities.
	 * @param dtoList list of DTOs to convert
	 * @return list of {@code Sensor} entities equivalent to the DTOs
	 */
	public static List<Sensor> toEntities(List<SensorDTO> dtoList) {
		List<Sensor> entities = new ArrayList<>();
		
		for (SensorDTO dto : dtoList) {
			entities.add(SensorConverter.toEntity(dto));
		}
		
		return entities;
	}
	
}
