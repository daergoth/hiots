package net.daergoth.serviceapi.actors;

import java.util.List;

import net.daergoth.serviceapi.actors.dummy.DummyActorVO;

/**
 * Defines all methods, that a Actor-provider service-bean must have.
 * Used for getting {@code Actor}s from an {@code ActorDAO}.
 * For it's default implementation check the Service layer.
 */
public interface ActorContainerLocal {
	
	/**
	 * Retrieves all {@code Actor}s from the database.
	 * 
	 * @return a list of actors
	 */
	public List<ActorVO> getActors();
	
	/**
	 * Retrieves all {@code DummyActor}s from the database.
	 * 
	 * @return a list of dummy actors
	 */
	public List<DummyActorVO> getDummyActors();
	
	/**
	 * Adds an {@code Actor} to the database.
	 * 
	 * @param actor the actor to persist
	 */
	public void addActor(ActorVO actor);
	
	/**
	 * Updates an {@code Actor} in the database.
	 * 
	 * @param actor the actor to update
	 */
	public void updateActor(ActorVO actor);
	
	/**
	 * Deletes an {@code Actor} from the database.
	 * 
	 * @param id the ID of the actor to delete
	 */
	public void deleteActor(long id);
	
	/**
	 * Deletes multiple {@code Actor}s from the database.
	 * 
	 * @param ids the list of ID of the actors to delete
	 */
	public void deleteActors(List<Long> ids);

}
