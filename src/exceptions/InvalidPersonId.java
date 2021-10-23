package exceptions;

@SuppressWarnings("serial")
public class InvalidPersonId extends RuntimeException {
	public InvalidPersonId() {
		super("The person id is invalid.");
	}
}
