package core.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.daergoth.core.rule.Action;
import net.daergoth.core.rule.Condition;
import net.daergoth.core.rule.Rule;
import net.daergoth.core.rule.RuleConverter;
import net.daergoth.coreapi.rule.ActionDTO;
import net.daergoth.coreapi.rule.ConditionDTO;
import net.daergoth.coreapi.rule.RuleDTO;

public class RuleConverterTest {
	
	static RuleDTO ruleDto;
	
	static Rule ruleEntity;

	@BeforeClass
	public static void setup() {
		List<ActionDTO> actionDtos = new ArrayList<>();
		List<ConditionDTO> condDtos = new ArrayList<>();
		ruleDto = new RuleDTO();
		ruleDto.setId(1l);
		ruleDto.setName("TestRule");
		ruleDto.setEnabled(true);
		ruleDto.setConditions(condDtos);
		ruleDto.setActions(actionDtos);
		
		List<Action> actionEntities = new ArrayList<>();
		List<Condition> condEntities = new ArrayList<>();
		ruleEntity = new Rule();
		ruleEntity.setId(1l);
		ruleEntity.setName("TestRule");
		ruleEntity.setEnabled(true);
		ruleEntity.setConditions(condEntities);
		ruleEntity.setActions(actionEntities);
	}

	@Test
	public void testToDTO() {
		RuleDTO newDto = RuleConverter.toDTO(ruleEntity);
		
		Assert.assertEquals("Not matching IDs!", ruleDto.getId(), newDto.getId());
		
		Assert.assertEquals("Not matching names!", ruleDto.getName(), newDto.getName());
		
		Assert.assertEquals("Not matching status!", ruleDto.isEnabled(), newDto.isEnabled());
	}

	@Test
	public void testToDTOs() {
		List<Rule> entityList = new ArrayList<>();
		entityList.add(ruleEntity);
		
		List<RuleDTO> dtoList = new ArrayList<>();
		dtoList.add(ruleDto);
		
		List<RuleDTO> actual = RuleConverter.toDTOs(entityList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", dtoList.get(i).getId(), actual.get(i).getId());
			
			Assert.assertEquals("Not matching names!", dtoList.get(i).getName(), actual.get(i).getName());
			
			Assert.assertEquals("Not matching status!", dtoList.get(i).isEnabled(), actual.get(i).isEnabled());
		}
	}

	@Test
	public void testToEntity() {
		Rule newEntity = RuleConverter.toEntity(ruleDto);
		
		Assert.assertEquals("Not matching IDs!", ruleEntity.getId(), newEntity.getId());
		
		Assert.assertEquals("Not matching names!", ruleEntity.getName(), newEntity.getName());
		
		Assert.assertEquals("Not matching status!", ruleEntity.isEnabled(), newEntity.isEnabled());
	}

	@Test
	public void testToEntities() {
		List<RuleDTO> dtoList = new ArrayList<>();
		dtoList.add(ruleDto);
		
		List<Rule> entityList = new ArrayList<>();
		entityList.add(ruleEntity);
		
		List<Rule> actual = RuleConverter.toEntities(dtoList);
		
		for (int i = 0; i < actual.size(); ++i) {
			Assert.assertEquals("Not matching IDs!", entityList.get(i).getId(), actual.get(i).getId());
			
			Assert.assertEquals("Not matching names!", entityList.get(i).getName(), actual.get(i).getName());
			
			Assert.assertEquals("Not matching status!", entityList.get(i).isEnabled(), actual.get(i).isEnabled());
		}
	}

}
