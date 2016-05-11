package core.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.actor.Actor;
import net.daergoth.core.actor.ActorState;
import net.daergoth.core.rule.Action;
import net.daergoth.core.rule.ActionConverter;
import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;
import net.daergoth.coreapi.rule.ActionDTO;

public class ActionConverterTest {

	private static ActorDTO actorDto;
	private static ActorStateDTO stateDto;
	private static ActionDTO actionDto;

	private static Actor actorEntity;
	private static ActorState stateEntity;
	private static Action actionEntity;

	@BeforeClass
	public static void setUpBeforeClass() {
		actorDto = new ActorDTO();
		actorDto.setId(1l);
		actorDto.setName("TestActor");
		actorDto.setType("Lamp");

		stateDto = new ActorStateDTO();
		stateDto.setType(ActorStateType.LAMP);
		stateDto.setValue(1.0);

		actionDto = new ActionDTO();
		actionDto.setId(1l);
		actionDto.setActor(actorDto);
		actionDto.setValue(stateDto);

		actorEntity = new Actor();
		actorEntity.setId(1l);
		actorEntity.setName("TestActor");
		actorEntity.setType("Lamp");

		stateEntity = new ActorState();
		stateEntity.setActorStateType(ActorStateType.LAMP);
		stateEntity.setActorStateValue(1.0);

		actionEntity = new Action();
		actionEntity.setId(1l);
		actionEntity.setActor(actorEntity);
		actionEntity.setValue(stateEntity);
	}

	@Test
	public void testToDTO() {
		ActionDTO newDto = ActionConverter.toDTO(actionEntity);

		Assert.assertEquals("Not matching IDs!", actionDto.getId(), newDto.getId());

		Assert.assertEquals("Wrong Actor in Action!", actionDto.getActor().getId(), newDto.getActor().getId());

		Assert.assertEquals("Wrong value in Action!", actionDto.getValue().getType(), newDto.getValue().getType());
	}

	@Test
	public void testToDTOs() {
		List<Action> entityList = new ArrayList<>();
		entityList.add(actionEntity);

		List<ActionDTO> dtoList = new ArrayList<>();
		dtoList.add(actionDto);

		List<ActionDTO> actual = ActionConverter.toDTOs(entityList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Wrong Actor in Action!", dtoList.get(i).getActor().getId(),
					actual.get(i).getActor().getId());

			Assert.assertEquals("Wrong value in Action!", dtoList.get(i).getValue().getType(),
					actual.get(i).getValue().getType());
		}

	}

	@Test
	public void testToEntity() {
		Action newEntity = ActionConverter.toEntity(actionDto);

		Assert.assertEquals("Not matching IDs!", actionEntity.getId(), newEntity.getId());

		Assert.assertEquals("Wrong Actor in Action!", actionEntity.getActor().getId(), newEntity.getActor().getId());

		Assert.assertEquals("Wrong value in Action!", actionEntity.getValue().getActorStateType(),
				newEntity.getValue().getActorStateType());
	}

	@Test
	public void testToEntities() {
		List<ActionDTO> dtoList = new ArrayList<>();
		dtoList.add(actionDto);

		List<Action> entityList = new ArrayList<>();
		entityList.add(actionEntity);

		List<Action> actual = ActionConverter.toEntities(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Wrong Actor in Action!", entityList.get(i).getActor().getId(),
					actual.get(i).getActor().getId());

			Assert.assertEquals("Wrong value in Action!", entityList.get(i).getValue().getActorStateType(),
					actual.get(i).getValue().getActorStateType());
		}
	}

}
