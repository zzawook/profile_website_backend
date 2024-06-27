package dev.kjaehyeok21.profile_website.repositories;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.kjaehyeok21.profile_website.entities.GithubRepository;


@Repository
public interface GitHubRepositoryRepository extends CrudRepository<GithubRepository, UUID> {
    
}
