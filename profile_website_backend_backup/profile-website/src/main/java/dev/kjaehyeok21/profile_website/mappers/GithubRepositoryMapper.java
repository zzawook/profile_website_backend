package dev.kjaehyeok21.profile_website.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.kjaehyeok21.profile_website.entities.GithubRepository;
import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;

@Mapper
public interface GithubRepositoryMapper {
    
    GetGithubRepositoryHolder githubRepositoryTogetGithubRepositoryHolder(GithubRepository githubRepository);

    @Mapping(target = "id", ignore = true)
    GithubRepository getGithubRepositoryHolderToGithubRepository(GetGithubRepositoryHolder getGithubRepositoryHolder);
}
