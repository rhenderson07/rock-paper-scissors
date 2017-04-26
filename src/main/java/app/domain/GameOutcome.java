package app.domain;

import java.util.Optional;

public class GameOutcome {
	private GameResult result;
	private Optional<Player> winner;

	public GameOutcome( GameResult result, Optional<Player> winner) {
		super();
		this.result = result;
		this.winner = winner;
	}

	public Optional<Player> getWinner() {
		return winner;
	}

	public GameResult getResult() {
		return result;
	}
}
