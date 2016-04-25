package net.daergoth.serviceapi;

import java.util.List;

import net.daergoth.serviceapi.actors.ActorVO;
import net.daergoth.serviceapi.actors.dummy.DummyActorVO;

public interface ActorContainerLocal {
	
	public List<ActorVO> getActors();
	
	public List<DummyActorVO> getDummyActors();
	
	public void addActor(ActorVO a);
	
	public void updateActor(ActorVO a);
	
	public void deleteActor(long id);
	
	public void deleteActors(List<Long> ids);

	public long getNewId();
	
}
