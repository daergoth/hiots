package core.actor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.actor.ActorState;
import net.daergoth.core.actor.ActorStateConverter;
import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;

public class ActorStateConverterTest {

	private static ActorStateDTO stateDto;

	private static ActorState stateEntity;

	@BeforeClass
	public static void setUpBeforeClass() {
		stateDto = new ActorStateDTO();
		stateDto.setType(ActorStateType.LAMP);
		stateDto.setValue(1.0);

		stateEntity = new ActorState();
		stateEntity.setActorStateType(ActorStateType.LAMP);
		stateEntity.setActorStateValue(1.0);
	}

	@Test
	public void testToDTO() {
		ActorStateDTO newDto = ActorStateConverter.toDTO(stateEntity);

		Assert.assertEquals("Not matching ActorStateType!", stateDto.getType(), newDto.getType());

		Assert.assertEquals("Not matching value!", stateDto.getValue(), newDto.getValue());
	}

	@Test
	public void testToDTOs() {
		List<ActorState> entityList = new ArrayList<>();
		entityList.add(stateEntity);

		List<ActorStateDTO> dtoList = new ArrayList<>();
		dtoList.add(stateDto);

		List<ActorStateDTO> actual = ActorStateConverter.toDTOs(entityList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching ActorStateType!", dtoList.get(i).getType(), actual.get(i).getType());

			Assert.assertEquals("Not matching value!", dtoList.get(i).getValue(), actual.get(i).getValue());
		}

	}

	@Test
	public void testToEntity() {
		ActorState newEntity = ActorStateConverter.toEntity(stateDto);

		Assert.assertEquals("Not matching ActorStateType!", stateEntity.getActorStateType(),
				newEntity.getActorStateType());

		Assert.assertEquals("Not matching value!", stateEntity.getActorStateValue(), newEntity.getActorStateValue());
	}

	@Test
	public void testToEntities() {
		List<ActorStateDTO> dtoList = new ArrayList<>();
		dtoList.add(stateDto);

		List<ActorState> entityList = new ArrayList<>();
		entityList.add(stateEntity);

		List<ActorState> actual = ActorStateConverter.toEntities(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching ActorStateType!", entityList.get(i).getActorStateType(),
					actual.get(i).getActorStateType());

			Assert.assertEquals("Not matching value!", entityList.get(i).getActorStateValue(),
					actual.get(i).getActorStateValue());
		}
	}

}
