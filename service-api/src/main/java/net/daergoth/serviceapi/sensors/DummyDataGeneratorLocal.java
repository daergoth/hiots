package net.daergoth.serviceapi.sensors;

import java.util.List;

import net.daergoth.serviceapi.sensors.dummy.DummySensorVO;

public interface DummyDataGeneratorLocal {
	
	public static final long MIN_UPDATE_INTERVAL = 5000;
	
	public void generateAllDummies();
	
	public void setDummiesList(List<DummySensorVO> dl);
	
	public void addDummy(DummySensorVO d);
	
	public void deleteDummy(DummySensorVO d);
	
}
