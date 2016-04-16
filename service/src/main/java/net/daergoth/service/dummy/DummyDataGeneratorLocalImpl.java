package net.daergoth.service.dummy;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import net.daergoth.serviceapi.dummy.DummyDataGeneratorLocal;
import net.daergoth.serviceapi.dummy.DummySensorVO;

@Stateless
@Local(DummyDataGeneratorLocal.class)
public class DummyDataGeneratorLocalImpl implements DummyDataGeneratorLocal {
	
	List<DummySensorVO> dummiesList = new ArrayList<>();
	
	public void generateAllDummies() {
		for (DummySensorVO dummySensorVO : dummiesList) {
			dummySensorVO.generateRandomData();
		}
	}

	@Override
	public void setDummiesList(List<DummySensorVO> dl) {
		this.dummiesList = dl;
	}

}
