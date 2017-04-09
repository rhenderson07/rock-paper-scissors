package app.model.exception;

public final class GameDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "Game '%d' does not exist";

	public GameDoesNotExistException(Integer gameId) {
		super(String.format(MESSAGE_FORMAT, gameId));
	}
}