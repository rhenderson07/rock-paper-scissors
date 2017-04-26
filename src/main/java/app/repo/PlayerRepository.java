package app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.domain.Player;

@RepositoryRestResource//(exported=false)
public interface PlayerRepository 
extends PagingAndSortingRepository<Player, Integer> 
{

}
