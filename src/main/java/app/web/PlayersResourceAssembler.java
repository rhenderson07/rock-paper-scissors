package app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import app.model.Game;
import app.model.Player;

@Component
final class PlayersResourceAssembler implements ResourceAssembler<Game, Resources<Resource<Player>>> {

	private final PlayerResourceAssembler playerResourceAssembler;

	@Autowired
	public PlayersResourceAssembler(PlayerResourceAssembler playerResourceAssembler) {
		this.playerResourceAssembler = playerResourceAssembler;
	}

	@Override
	public Resources<Resource<Player>> toResource(Game game) {
		List<Resource<Player>> playerResources = new ArrayList<>();

//		for (Player player : game.getPlayers()) {
//			playerResources.add(this.playerResourceAssembler.toResource(game, player));
//		}

		Resources<Resource<Player>> resources = new Resources<>(playerResources);
		resources.add(linkTo(GamesController.class).slash(game.getId()).slash("players").withSelfRel());

		return resources;
	}
}