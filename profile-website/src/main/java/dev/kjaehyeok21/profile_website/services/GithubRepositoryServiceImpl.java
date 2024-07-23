package dev.kjaehyeok21.profile_website.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.kjaehyeok21.profile_website.entities.GithubRepository;
import dev.kjaehyeok21.profile_website.mappers.GithubRepositoryMapper;
import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import dev.kjaehyeok21.profile_website.repositories.GitHubRepositoryRepository;

@Service
public class GithubRepositoryServiceImpl implements GithubRepositoryService{

    private final GitHubRepositoryRepository gitHubRepositoryRepository;

    private final GithubRepositoryMapper githubRepositoryMapper;

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
            .uri("https://api.github.com/users/zzawook/repos")
            .retrieve()
            .bodyToMono(String.class)
            .map(str -> {
                JSONArray jsonArray = new JSONArray(str);
                List<GithubRepository> gitHubRepositories = new ArrayList<GithubRepository>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Integer id = jsonObject.getInt("id");
                    String repoName = this.getFromJsonObject(jsonObject, "name");
                    String repoUrl = this.getFromJsonObject(jsonObject, "html_url");
                    String description = this.getFromJsonObject(jsonObject, "description");
                    String createdAt = this.getFromJsonObject(jsonObject, "created_at");
                    String updatedAt = this.getFromJsonObject(jsonObject, "updated_at");
                    String language = this.getFromJsonObject(jsonObject, "language");

                    LocalDateTime createdDT = null, updatedDT = null;

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        createdDT = sdf.parse(createdAt).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        updatedDT = sdf.parse(updatedAt).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    }
                    catch(ParseException e) {
                        e.printStackTrace();
                        continue;
                    }
                    
                
                    GithubRepository currentRepository = GithubRepository.builder()
                        .id(id)
                        .repoName(repoName)
                        .repoUrl(repoUrl)
                        .description(description)
                        .createdAt(createdDT)
                        .updatedAt(updatedDT)
                        .language(language)
                        .build();

                    gitHubRepositories.add(currentRepository);
                };

                return gitHubRepositories;
            })
            .subscribe(gitHubRepositories -> {
                for (int i = 0; i < gitHubRepositories.size(); i++) {
                    GithubRepository gitHubRepository = gitHubRepositories.get(i);
                    gitHubRepositoryRepository.save(gitHubRepository);
                }
            });
    }

    private String getFromJsonObject(JSONObject jsonObject, String key) {
        return jsonObject.isNull(key) ? "" : jsonObject.getString(key);
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
