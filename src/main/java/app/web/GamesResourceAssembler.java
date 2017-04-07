package app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import app.model.Game;

@Component
final class GamesResourceAssembler implements ResourceAssembler<Collection<Game>, Resources<Resource<Game>>> {

	private final GameResourceAssembler gameResourceAssembler;

	@Autowired
	public GamesResourceAssembler(GameResourceAssembler gameResourceAssembler) {
		this.gameResourceAssembler = gameResourceAssembler;
	}

	@Override
	public Resources<Resource<Game>> toResource(Collection<Game> games) {
		List<Resource<Game>> gameResources = games.stream() //
				.map(gameResourceAssembler::toResource) //
				.collect(Collectors.toList());

		Resources<Resource<Game>> resource = new Resources<>(gameResources);
		resource.add(linkTo(GamesController.class).withSelfRel());

		return resource;
	}
}