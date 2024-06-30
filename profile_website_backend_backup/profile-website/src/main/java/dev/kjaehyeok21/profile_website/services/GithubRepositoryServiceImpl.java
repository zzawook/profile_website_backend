package dev.kjaehyeok21.profile_website.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kjaehyeok21.profile_website.entities.GithubRepository;
import dev.kjaehyeok21.profile_website.mappers.GithubRepositoryMapper;
import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import dev.kjaehyeok21.profile_website.repositories.GitHubRepositoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubRepositoryServiceImpl implements GithubRepositoryService{

    @Autowired
    GitHubRepositoryRepository gitHubRepositoryRepository;

    @Autowired
    GithubRepositoryMapper githubRepositoryMapper;

    @Override
    public List<GetGithubRepositoryHolder> getRepositoryList() {
        Iterable<GithubRepository> githubRepos = gitHubRepositoryRepository.findAll();
        
        List<GetGithubRepositoryHolder> getGithubRepositoryHolders = new ArrayList<GetGithubRepositoryHolder>();
        githubRepos.forEach(gitHubRepository -> {
            getGithubRepositoryHolders.add(githubRepositoryMapper.githubRepositoryTogetGithubRepositoryHolder(gitHubRepository));
        });
        
        return getGithubRepositoryHolders;
    }
}
