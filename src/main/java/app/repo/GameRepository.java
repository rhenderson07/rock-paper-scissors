package app.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import app.model.ComputerPlayer;
import app.model.Game;
import app.model.Player;
import app.model.exception.GameDoesNotExistException;

@Repository
public class GameRepository {

	private final AtomicInteger idGenerator = new AtomicInteger();
	private final Object monitor = new Object();
	private Map<Integer, Game> games = new HashMap<>();

	public Game createGame() {
		synchronized (this.monitor) {
			Game game = new Game(idGenerator.getAndIncrement(), buildPlayers());
			games.put(game.getGameId(), game);
			return game;
		}
	}

	public Game retrieve(int gameId) throws GameDoesNotExistException {
		if (!games.containsKey(gameId)){
			throw new GameDoesNotExistException(gameId);
		}
		
		return games.get(gameId);
	}

	public Collection<Game> retrieveAll() {
		return games.values();
	}

	public void delete(int gameId) {
		games.remove(gameId);
	}

	private List<Player> buildPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player(0));
		players.add(new ComputerPlayer(1));
		return players;
	}

}
