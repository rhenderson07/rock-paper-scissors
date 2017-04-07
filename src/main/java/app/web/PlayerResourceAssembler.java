package app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import app.model.Game;
import app.model.Player;

@Component
final class PlayerResourceAssembler {

	public Resource<Player> toResource(Game game, Player player) {
		Resource<Player> resource = new Resource<>(player);
		resource.add(linkTo(GamesController.class).slash(game.getGameId()).slash("players").slash(player.getPlayerId())
				.withSelfRel());
		return resource;
	}

}