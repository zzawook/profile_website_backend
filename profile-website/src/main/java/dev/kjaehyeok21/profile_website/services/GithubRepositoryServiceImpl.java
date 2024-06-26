package dev.kjaehyeok21.profile_website.services;

import org.springframework.stereotype.Service;

import dev.kjaehyeok21.profile_website.mappers.GithubRepositoryMapper;
import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import dev.kjaehyeok21.profile_website.repositories.GitHubRepositoryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GithubRepositoryServiceImpl implements GithubRepositoryService{

    GitHubRepositoryRepository gitHubRepositoryRepository;
    GithubRepositoryMapper githubRepositoryMapper;

    @Override
    public Flux<GetGithubRepositoryHolder> getRepositoryList() {
        return gitHubRepositoryRepository.findAll().map(gitHubRepository -> githubRepositoryMapper.githubRepositoryTogetGithubRepositoryHolder(gitHubRepository));
    }
}
