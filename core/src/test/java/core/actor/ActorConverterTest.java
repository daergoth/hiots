package core.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.actor.Actor;
import net.daergoth.core.actor.ActorConverter;
import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.DummyActorDTO;

public class ActorConverterTest {

	private static ActorDTO actorDto;
	private static DummyActorDTO dummyActorDto;

	private static Actor actorEntity;
	private static Actor dummyActorEntity;

	@BeforeClass
	public static void setUpBeforeClass() {
		actorDto = new ActorDTO();
		actorDto.setId(1l);
		actorDto.setName("TestActor");
		actorDto.setType("Lamp");

		dummyActorDto = new DummyActorDTO();
		dummyActorDto.setId(2l);
		dummyActorDto.setName("TestDummyActor");
		dummyActorDto.setType("Thermostat");

		actorEntity = new Actor();
		actorEntity.setId(1l);
		actorEntity.setName("TestActor");
		actorEntity.setType("Lamp");

		dummyActorEntity = new Actor();
		dummyActorEntity.setId(2l);
		dummyActorEntity.setName("TestDummyActor");
		dummyActorEntity.setType("Thermostat");
	}

	@Test
	public void testToDTO() {
		ActorDTO newDto = ActorConverter.toDTO(actorEntity);

		Assert.assertEquals("Not matching IDs!", actorDto.getId(), newDto.getId());

		Assert.assertEquals("Not matching names!", actorDto.getName(), newDto.getName());

		Assert.assertEquals("Not matching Actor type!", actorDto.getType(), newDto.getType());

	}

	@Test
	public void testToDTOs() {
		List<Actor> entityList = new ArrayList<>();
		entityList.add(actorEntity);
		entityList.add(dummyActorEntity);

		List<ActorDTO> dtoList = new ArrayList<>();
		dtoList.add(actorDto);
		dtoList.add(dummyActorDto);

		List<ActorDTO> actual = ActorConverter.toDTOs(entityList);

		for (int i = 0; i < actual.size(); i++) {
			if (dtoList.get(i) instanceof DummyActorDTO) {
				Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

				Assert.assertEquals("Not matching names!", dtoList.get(i).getName(), actual.get(i).getName());

				Assert.assertEquals("Not matching Actor type!", dtoList.get(i).getType(), actual.get(i).getType());

			} else {
				/*
				 * if (actual.get(i) instanceof DummyActorDTO) { 
				 * 	Assert.fail("Return is DummyActorDTO for Actor entity!"); 
				 * }
				 */

				Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

				Assert.assertEquals("Not matching names!", dtoList.get(i).getName(), actual.get(i).getName());

				Assert.assertEquals("Not matching Actor type!", dtoList.get(i).getType(), actual.get(i).getType());
			}

		}
	}

	@Test
	public void testToEntity() {
		Actor newEntity = ActorConverter.toEntity(actorDto);

		Assert.assertEquals("Not matching IDs!", actorEntity.getId(), newEntity.getId());

		Assert.assertEquals("Not matching names!", actorEntity.getName(), newEntity.getName());

		Assert.assertEquals("Not matching Actor type!", actorEntity.getType(), newEntity.getType());

		newEntity = ActorConverter.toEntity(dummyActorDto);

		Assert.assertEquals("Not matching IDs!", dummyActorEntity.getId(), newEntity.getId());

		Assert.assertEquals("Not matching names!", dummyActorEntity.getName(), newEntity.getName());

		Assert.assertEquals("Not matching Actor type!", dummyActorEntity.getType(), newEntity.getType());

	}

	@Test
	public void testToEntities() {
		List<ActorDTO> dtoList = new ArrayList<>();
		dtoList.add(actorDto);
		dtoList.add(dummyActorDto);
		
		List<Actor> entityList = new ArrayList<>();
		entityList.add(actorEntity);
		entityList.add(dummyActorEntity);

		List<Actor> actual = ActorConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching names!", entityList.get(i).getName(), actual.get(i).getName());

			Assert.assertEquals("Not matching Actor type!", entityList.get(i).getType(), actual.get(i).getType());
		}
	}

}
