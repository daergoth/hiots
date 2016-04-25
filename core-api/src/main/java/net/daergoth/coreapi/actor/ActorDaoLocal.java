package net.daergoth.coreapi.actor;

import java.util.List;

public interface ActorDaoLocal {

	public List<ActorDTO> getActors();

	public void addActor(ActorDTO a);

	public void updateActor(ActorDTO a);

	public void deleteActor(long id);

	public void deleteActors(List<Long> ids);

}
