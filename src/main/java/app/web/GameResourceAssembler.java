package app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import app.model.Game;

@Component
final class GameResourceAssembler implements ResourceAssembler<Game, Resource<Game>> {

	@Override
	public Resource<Game> toResource(Game game) {
		Resource<Game> resource = new Resource<>(game);
		resource.add(linkTo(GamesController.class).slash(game.getGameId()).withSelfRel());
		resource.add(linkTo(GamesController.class).slash(game.getGameId()).slash("players").withRel("players"));
		resource.add(linkTo(GamesController.class).slash(game.getGameId()).withRel("destroy"));
		
		return resource;
	}
}