package net.daergoth.serviceapi.actors;

/**
 * Exception thrown, when an {@code Actor} was tried to set to an {@code ActorState} with wrong type.
 *
 * @see net.daergoth.serviceapi.actors.ActorVO
 */
public class InvalidActorStateTypeException extends Exception {

	private static final long serialVersionUID = -6417490782212597019L;

	/**
	 * Constructs a new {@code InvalidActorStateTypeException}.
	 * 
	 * @param msg the message shown when the exception occurs
	 */
	public InvalidActorStateTypeException(String msg) {
		super(msg);
	}
	
	
	
}
