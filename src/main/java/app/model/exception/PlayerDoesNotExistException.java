package app.model.exception;

public final class PlayerDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "Player '%d' in game '%d' does not exist";

	public PlayerDoesNotExistException(Integer gameId, Integer player) {
		super(String.format(MESSAGE_FORMAT, player, gameId));
	}
}