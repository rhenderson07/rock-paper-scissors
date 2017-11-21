package app.domain.exception;

public final class ElementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "Element '%d' does not exist";

	public ElementNotFoundException(Integer gameId) {
		super(String.format(MESSAGE_FORMAT, gameId));
	}

	public ElementNotFoundException(String message) {
		super(message);
	}
}