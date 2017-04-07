package app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
	private final int gameId;
	private final List<Player> players;
	private GameStatus status;
	private GameOutcome outcome;

	public Game(int gameId, Collection<Player> players) {
		this.gameId = gameId;
		this.players = new ArrayList<>(players);
		this.status = GameStatus.AWAITING_PLAYER_ACTIONS;
	}

	public int getGameId() {
		return gameId;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void selectAction(int playerId, PlayerAction action) {
		players.get(playerId).setAction(action);

		boolean allPlayersReady = players.stream().allMatch(x -> x.getStatus() == PlayerStatus.ACTION_SELECTED);

		if (allPlayersReady) {
			resolveActions(players);
		}
	}

	public Optional<GameOutcome> getOutcome() {
		return Optional.ofNullable(outcome);
	}

	private void resolveActions(List<Player> players) {
		players.forEach(Player::reveal);
		status = GameStatus.COMPLETE;

		Optional<Player> winnerOpt = determineWinner(players);
		outcome = determineOutcome(winnerOpt);
	}

	private Optional<Player> determineWinner(List<Player> players) {
		Set<PlayerAction> actionsRevealed = players.stream() //
				.map(Player::getAction) //
				.collect(Collectors.toSet());

		if (actionsRevealed.size() == 1) {
			return Optional.empty();
		}

		Set<PlayerAction> losingActions = actionsRevealed.stream() //
				.map(x -> getMatchUps().get(x)) //
				.collect(Collectors.toSet());

		return players.stream().filter(x -> !losingActions.contains(x.getAction())).findAny();
	}

	private Map<PlayerAction, PlayerAction> getMatchUps() {
		Map<PlayerAction, PlayerAction> matchups = new HashMap<>();

		matchups.put(PlayerAction.ROCK, PlayerAction.SCISSORS);
		matchups.put(PlayerAction.SCISSORS, PlayerAction.PAPER);
		matchups.put(PlayerAction.PAPER, PlayerAction.ROCK);

		return matchups;
	}

	private GameOutcome determineOutcome(Optional<Player> winner) {
		GameResult result;
		if (!winner.isPresent()) {
			result = GameResult.TIE;
		} else {
			switch (winner.get().getAction()) {
			case ROCK:
				result = GameResult.ROCK_WIN;
				break;
			case PAPER:
				result = GameResult.PAPER_WIN;
				break;
			case SCISSORS:
				result = GameResult.SCISSOR_WIN;
				break;
			default:
				throw new IllegalStateException();
			}
		}

		return new GameOutcome(result, winner);
	}
}