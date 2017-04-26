package app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.repo.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;

	private List<Player> buildPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player());
		players.add(new ComputerPlayer());
		return players;
	}

	public Game createNewGame() {
		Game game = new Game();
		setToInitialState(game);
				
		return gameRepository.save(game);
	}
	
	public void setToInitialState(Game game){
		game.setPlayers(buildPlayers());
		game.setStatus(GameStatus.AWAITING_PLAYER_ACTIONS);
	}
}
