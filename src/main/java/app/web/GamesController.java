package app.web;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.Game;
import app.model.Player;
import app.model.PlayerAction;
import app.repo.GameRepository;

@RestController
@RequestMapping(value = "/games", produces = "application/vnd.api+json")
public class GamesController {

	@Autowired
	private GameResourceAssembler gameResourceAssembler;

	@Autowired
	private GamesResourceAssembler gamesResourceAssembler;

	@Autowired
	private PlayersResourceAssembler playersResourceAssembler;

	@Autowired
	private GameRepository gameService;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Resource<Game> createGame() {
		Game game = gameService.createGame();
		return gameResourceAssembler.toResource(game);
	}

	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public Resources<Resource<Game>> showAllGames() {
		Collection<Game> games = gameService.retrieveAll();
		return gamesResourceAssembler.toResource(games);
	}

	@GetMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Resource<Game> showGame(@PathVariable Integer gameId) {
		Game game = gameService.retrieve(gameId);
		return gameResourceAssembler.toResource(game);
	}

	@DeleteMapping("/{gameId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void destroyGame(@PathVariable Integer gameId) {
		gameService.delete(gameId);
	}

	@GetMapping("/{gameId}/players")
	@ResponseStatus(code = HttpStatus.OK)
	public Resources<Resource<Player>> showPlayers(@PathVariable Integer gameId) {
		Game game = gameService.retrieve(gameId);
		return playersResourceAssembler.toResource(game);
	}

	@PutMapping("/{gameId}/players/{playerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void setPlayerAction(@PathVariable Integer gameId, @PathVariable Integer playerId,
			@RequestParam PlayerAction action) {
		Game game = gameService.retrieve(gameId);
		game.selectAction(playerId, action);
	}

	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
		response.setHeader("Vary", "Accept");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "Accept");
	}
}
