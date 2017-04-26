package app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.model.Game;
import app.web.GameProjection;

@RepositoryRestResource (excerptProjection = GameProjection.class)
public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {

}
