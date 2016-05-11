package service.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.actor.ActorDTO;
import net.daergoth.coreapi.actor.ActorStateDTO;
import net.daergoth.coreapi.actor.ActorStateType;
import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.service.rule.ActionConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.LampActorVO;
import net.daergoth.serviceapi.actors.states.ActorStateVO;
import net.daergoth.serviceapi.actors.states.LampActorStateVO;
import net.daergoth.serviceapi.rule.ActionVO;

@FixMethodOrder(MethodSorters.JVM)
public class ActionConverterTest {

	private static ActionVO actionVo;
	private static ActorVO actorVo;
	private static ActorStateVO stateVo;

	private static ActionDTO actionDto;
	private static ActorDTO actorDto;
	private static ActorStateDTO stateDto;

	@BeforeClass
	public static void setUpBeforeClass() {
		actorVo = new LampActorVO(7l, "LampActor");

		stateVo = new LampActorStateVO();
		stateVo.setData(1.0);

		actionVo = new ActionVO();
		actionVo.setId(1l);
		actionVo.setActor(actorVo);
		actionVo.setValue(stateVo);

		actorDto = new ActorDTO();
		actorDto.setId(7l);
		actorDto.setName("LampActor");
		actorDto.setType("Lamp");

		stateDto = new ActorStateDTO();
		stateDto.setType(ActorStateType.LAMP);
		stateDto.setValue(1.0);

		actionDto = new ActionDTO();
		actionDto.setId(1l);
		actionDto.setActor(actorDto);
		actionDto.setValue(stateDto);
	}

	@Test
	public void testToVO() throws ActorConvertException {
		ActionVO newVo = ActionConverter.toVO(actionDto);

		Assert.assertEquals("Not matching IDs!", actionVo.getId(), newVo.getId());

		Assert.assertEquals("Wrong Actor in Action!", actionVo.getActor().getId(), newVo.getActor().getId());

		Assert.assertEquals("Not matching Value type!", actionVo.getValue().getType(), newVo.getValue().getType());

		Assert.assertEquals("Not matching Value data", actionVo.getValue().getData(), newVo.getValue().getData());
	}

	@Test
	public void testToVOs() throws ActorConvertException {
		List<ActionDTO> dtoList = new ArrayList<>();
		dtoList.add(actionDto);

		List<ActionVO> voList = new ArrayList<>();
		voList.add(actionVo);

		List<ActionVO> actual = ActionConverter.toVOs(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", voList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Wrong Actor in Action!", voList.get(i).getActor().getId(),
					actual.get(i).getActor().getId());

			Assert.assertEquals("Not matching Value type!", voList.get(i).getValue().getType(),
					actual.get(i).getValue().getType());

			Assert.assertEquals("Not matching Value data", voList.get(i).getValue().getData(),
					actual.get(i).getValue().getData());
		}

	}

	@Test
	public void testToDTO() {
		ActionDTO newDto = ActionConverter.toDTO(actionVo);

		Assert.assertEquals("Not matching IDs!", actionDto.getId(), newDto.getId());

		Assert.assertEquals("Wrong Actor in Action!", actionDto.getActor().getId(), newDto.getActor().getId());

		Assert.assertEquals("Not matching Value type!", actionDto.getValue().getType(), newDto.getValue().getType());

		Assert.assertEquals("Not matching Value data", actionDto.getValue().getValue(), newDto.getValue().getValue());
	}

	@Test
	public void testToDTOs() {
		List<ActionVO> voList = new ArrayList<>();
		voList.add(actionVo);

		List<ActionDTO> dtoList = new ArrayList<>();
		dtoList.add(actionDto);

		List<ActionDTO> actual = ActionConverter.toDTOs(voList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Wrong Actor in Action!", dtoList.get(i).getActor().getId(),
					actual.get(i).getActor().getId());

			Assert.assertEquals("Not matching Value type!", dtoList.get(i).getValue().getType(),
					actual.get(i).getValue().getType());

			Assert.assertEquals("Not matching Value data", dtoList.get(i).getValue().getValue(),
					actual.get(i).getValue().getValue());
		}
	}

}
