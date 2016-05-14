package net.daergoth.serviceapi.actors;

/**
 * Exception thrown, when an {@code ActorDTO} cannot be converted to {@code ActorVO}.
 * 
 * @see net.daergoth.service.actor.ActorConverter
 */
public class ActorConvertException extends Exception {

	private static final long serialVersionUID = -4994037196546277297L;

	/**
	 * Constructs a new {@code ActorConvertException}.
	 * 
	 * @param msg the message shown when the exception occurs
	 */
	public ActorConvertException(String msg) {
		super(msg);
	}
}
