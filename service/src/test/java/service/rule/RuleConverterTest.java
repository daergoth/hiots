package service.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.RuleDTO;
import net.daergoth.service.rule.RuleConverter;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.rule.ActionVO;
import net.daergoth.serviceapi.rule.ConditionVO;
import net.daergoth.serviceapi.rule.RuleVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

@FixMethodOrder(MethodSorters.JVM)
public class RuleConverterTest {

	private static RuleVO ruleVo;

	private static RuleDTO ruleDto;

	@BeforeClass
	public static void setUpBeforeClass() {
		List<ConditionVO> conditionVOs = new ArrayList<>();
		List<ActionVO> actionVOs = new ArrayList<>();

		ruleVo = new RuleVO();
		ruleVo.setId(1l);
		ruleVo.setName("TestRule");
		ruleVo.setEnabled(true);
		ruleVo.setConditions(conditionVOs);
		ruleVo.setActions(actionVOs);

		List<ConditionDTO> conditionDTOs = new ArrayList<>();
		List<ActionDTO> actionDTOs = new ArrayList<>();

		ruleDto = new RuleDTO();
		ruleDto.setId(1l);
		ruleDto.setName("TestRule");
		ruleDto.setEnabled(true);
		ruleDto.setConditions(conditionDTOs);
		ruleDto.setActions(actionDTOs);
	}

	@Test
	public void testToVO() throws SensorConvertException, ActorConvertException {
		RuleVO newVo = RuleConverter.toVO(ruleDto);

		Assert.assertEquals("Not matching IDs!", ruleVo.getId(), newVo.getId());

		Assert.assertEquals("Not matching names!", ruleVo.getName(), newVo.getName());

		Assert.assertEquals("Not matching status!", ruleVo.isEnabled(), newVo.isEnabled());
	}

	@Test
	public void testToVOs() throws SensorConvertException, ActorConvertException {
		List<RuleDTO> dtoList = new ArrayList<>();
		dtoList.add(ruleDto);

		List<RuleVO> voList = new ArrayList<>();
		voList.add(ruleVo);

		List<RuleVO> actual = RuleConverter.toVOs(dtoList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", voList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching names!", voList.get(i).getName(), actual.get(i).getName());

			Assert.assertEquals("Not matching status!", voList.get(i).isEnabled(), actual.get(i).isEnabled());
		}

	}

	@Test
	public void testToDTO() {
		RuleDTO newDto = RuleConverter.toDTO(ruleVo);

		Assert.assertEquals("Not matching IDs!", ruleDto.getId(), newDto.getId());

		Assert.assertEquals("Not matching names!", ruleDto.getName(), newDto.getName());

		Assert.assertEquals("Not matching status!", ruleDto.isEnabled(), newDto.isEnabled());
	}

	@Test
	public void testToDTOs() {
		List<RuleVO> voList = new ArrayList<>();
		voList.add(ruleVo);

		List<RuleDTO> dtoList = new ArrayList<>();
		dtoList.add(ruleDto);

		List<RuleDTO> actual = RuleConverter.toDTOs(voList);

		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());

			Assert.assertEquals("Not matching names!", dtoList.get(i).getName(), actual.get(i).getName());

			Assert.assertEquals("Not matching status!", dtoList.get(i).isEnabled(), actual.get(i).isEnabled());
		}
	}

}
