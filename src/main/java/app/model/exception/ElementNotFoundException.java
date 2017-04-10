package app.model.exception;

public final class ElementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "Game '%d' does not exist";

	public ElementNotFoundException(Integer gameId) {
		super(String.format(MESSAGE_FORMAT, gameId));
	}
}