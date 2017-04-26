package app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.model.Player;

@RepositoryRestResource
public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {

}
