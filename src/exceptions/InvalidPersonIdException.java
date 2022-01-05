package exceptions;
/**
 * Represents the situation in which the person id is not valid, for one or more reasons.
 * @author Oihan and Eneko
 */
@SuppressWarnings("serial")
public class InvalidPersonIdException extends Exception {
	/**
	 * Sets up this exception with an appropriate message.
	 */
	public InvalidPersonIdException() {
		super("The person id is invalid.");
	}
}
