package app.domain.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.player.ComputerPlayer;
import app.domain.player.Player;
import app.repo.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
//	@Autowired
//	private PlayerRepository playerRepository;

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
