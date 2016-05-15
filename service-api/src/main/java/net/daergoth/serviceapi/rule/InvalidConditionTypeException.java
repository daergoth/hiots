package net.daergoth.serviceapi.rule;

/**
 * Exception thrown when a {@code Condition} has invalid type.
 * 
 * @see net.daergoth.serviceapi.rule.ConditionVO
 */
public class InvalidConditionTypeException extends Exception {

	private static final long serialVersionUID = -4883078345021685263L;

	/**
	 * Constructs a new {@code InvalidConditionTypeException}.
	 * @param message the message shown when the exception occurs
	 */
	public InvalidConditionTypeException(String message) {
		super(message);
	}
	
	

}
