package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.springframework.hateoas.Identifiable;

@Entity
public class Game implements Identifiable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer gameId;

	private GameStatus status;

	@OrderColumn
	@Column(unique = true)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Player> players;

	public Game() {
		this(new ArrayList<>());
	}

	public Game(List<Player> players) {
		this.players = players;
	}

	@Override
	public Integer getId() {
		return gameId;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void selectAction(int playerId, PlayerAction action) {
		players.get(playerId).setAction(action);

		boolean allPlayersReady = players.stream().allMatch(x -> x.getStatus() == PlayerStatus.ACTION_SELECTED);

		if (allPlayersReady) {
			resolveActions(players);
		}
	}

	private void resolveActions(List<Player> players) {
		players.forEach(Player::reveal);
		status = GameStatus.COMPLETE;

		Optional<Player> winnerOpt = determineWinner(players);
		// outcome = determineOutcome(winnerOpt);
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

	// private GameOutcome determineOutcome(Optional<Player> winner) {
	// GameResult result;
	// if (!winner.isPresent()) {
	// result = GameResult.TIE;
	// } else {
	// switch (winner.get().getAction()) {
	// case ROCK:
	// result = GameResult.ROCK_WIN;
	// break;
	// case PAPER:
	// result = GameResult.PAPER_WIN;
	// break;
	// case SCISSORS:
	// result = GameResult.SCISSOR_WIN;
	// break;
	// default:
	// throw new IllegalStateException();
	// }
	// }
	//
	// return new GameOutcome(result, winner);
	// }
}