package app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import app.domain.game.Game;
import app.domain.game.GameService;

@Component
@RepositoryEventHandler(Game.class)
public class GamesEventHandler {

	@Autowired
	private GameService gameService;

	@HandleBeforeCreate
	public void handleGameCreate(Game game) {
		gameService.setToInitialState(game);
	}
}
