package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.ActorVO;

/**
 * Base class for all types of {@code DummyActor}s.
 * Every class that represents a type of an {@code Actor}, which is simulated,
 * must extend this class.
 * <p>
 * Also since this is a base for more specific {@code Actor} classes,
 * you should only use it for inheriting base fields and methods.
 * 
 */
public abstract class DummyActorVO extends ActorVO {

	private static final long serialVersionUID = 8443695062688668343L;
	
	/**
	 * Constructs a new type-less {@code DummyActor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 */
	public DummyActorVO() {
		super();
	}

	/**
	 * Constructs a new type-less {@code DummyActor}.
	 * Shouldn't be used, except in subclass constructors.
	 * For more information see class documentation.
	 * 
	 * @param id  the ID of the new simulated actor
	 * @param name  the name of the new simulated actor
	 */
	public DummyActorVO(long id, String name) {
		super(id, name);
	}

}
