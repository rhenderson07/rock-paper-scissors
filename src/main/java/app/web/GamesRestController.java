package app.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.ComputerPlayer;
import app.model.Game;
import app.model.GameService;
import app.model.GameStatus;
import app.model.Player;
import app.repo.GameRepository;

@RepositoryRestController
public class GamesRestController {
	
//	@Autowired
//	private GameService gameService;
	
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
		game.setPlayers(buildPlayers());
		game.setStatus(GameStatus.AWAITING_PLAYER_ACTIONS);
				
		return gameRepository.save(game);
	}

//    @RequestMapping(value = "/games", method = RequestMethod.POST)
//    @ResponseBody
//    public Game createGame() {
//        return createNewGame(); 
//    }
}
