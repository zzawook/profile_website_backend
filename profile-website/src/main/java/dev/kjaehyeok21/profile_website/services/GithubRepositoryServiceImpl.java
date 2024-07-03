package dev.kjaehyeok21.profile_website.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public GithubRepositoryServiceImpl(GitHubRepositoryRepository gitHubRepositoryRepository, GithubRepositoryMapper githubRepositoryMapper) {
        this.gitHubRepositoryRepository = gitHubRepositoryRepository;
        this.githubRepositoryMapper = githubRepositoryMapper;

        this.fetchGithubRepositoryDataFromGithub();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void fetchGithubRepositoryDataDaily() {
        this.fetchGithubRepositoryDataFromGithub();
    }

    private void fetchGithubRepositoryDataFromGithub() {
        WebClient webClient = WebClient.create();
        webClient.get()
            .uri("https://api.github.com/users/kjaehyeok21/repos")
            .retrieve()
            .bodyToFlux(String.class)
            .map(str -> {
                JSONObject jsonObject = new JSONObject(str);
                String id = jsonObject.getString("id");
                String repoName = jsonObject.getString("name");
                String repoUrl = jsonObject.getString("html_url");
                String description = jsonObject.getString("description");
                String createdAt = jsonObject.getString("created_at");
                String updatedAt = jsonObject.getString("updated_at");
                String language = jsonObject.getString("language");
            
                return GithubRepository.builder()
                    .id(Integer.parseInt(id))
                    .repoName(repoName)
                    .repoUrl(repoUrl)
                    .description(description)
                    .createdAt(LocalDateTime.parse(createdAt))
                    .updatedAt(LocalDateTime.parse(updatedAt))
                    .language(language)
                    .build();
            })
            .subscribe(gitHubRepository -> {
                gitHubRepositoryRepository.save(gitHubRepository);
            });
    }

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
