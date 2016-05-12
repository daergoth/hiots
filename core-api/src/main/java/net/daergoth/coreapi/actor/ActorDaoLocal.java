package net.daergoth.coreapi.actor;

import java.util.List;

/**
 *  Provides access to database for obtaining {@code Actor} objects.
 *     
 *  @see net.daergoth.coreapi.actor.ActorDTO
 */
public interface ActorDaoLocal {

	/**
	 * Obtains all {@code Actor}s from the database.
	 * @return the list of actors
	 */
	public List<ActorDTO> getActors();

	/**
	 * Persists an {@code Actor} by writing it into the database.
	 * @param actor the actor to persist
	 */
	public void addActor(ActorDTO actor);

	/**
	 * Updates an {@code Actor}'s data in the database.
	 * @param actor the actor to update
	 */
	public void updateActor(ActorDTO actor);

	/**
	 * Deletes an {@code Actor} from the database .
	 * @param id the ID of the actor that will be deleted
	 */
	public void deleteActor(long id);

	/**
	 * Deletes multiple {@code Actor}s from the database.
	 * @param ids the list of IDs of actors that will be deleted
	 */
	public void deleteActors(List<Long> ids);

}
