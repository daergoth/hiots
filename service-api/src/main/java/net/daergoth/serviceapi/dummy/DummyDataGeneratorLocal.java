package net.daergoth.serviceapi.dummy;

import java.util.List;

public interface DummyDataGeneratorLocal {
	
	public void generateAllDummies();
	
	public void setDummiesList(List<DummySensorVO> dl);
	
}
