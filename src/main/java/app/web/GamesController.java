package app.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.Game;
import app.model.Player;
import app.model.PlayerAction;
import app.service.GameService;

@RestController
@RequestMapping("/games")
public class GamesController {

	@Autowired
	private GameService gameService;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Game createGame() {
		return gameService.createGame();
	}

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Game> getAllGames() {
		return gameService.retrieveAll();
	}

	@GetMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Game getGame(@PathVariable Integer gameId) {
		return gameService.retrieve(gameId);
	}

	@DeleteMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteGame(@PathVariable Integer gameId) {
		gameService.deleteGame(gameId);
	}

	@GetMapping("/{gameId}/players")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Player> showPlayers(@PathVariable Integer gameId){
		Game game =  gameService.retrieve(gameId);		
		return game.getPlayers();
	}
	
	@PostMapping("/{gameId}/players/{playerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void setPlayerAction(@PathVariable Integer gameId, @PathVariable Integer playerId, @RequestParam PlayerAction action){
		Game game =  gameService.retrieve(gameId);
		game.selectAction(playerId, action);
	}
}
