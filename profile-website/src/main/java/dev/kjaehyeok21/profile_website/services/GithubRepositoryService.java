package dev.kjaehyeok21.profile_website.services;

import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import reactor.core.publisher.Flux;

public interface GithubRepositoryService {

    Flux<GetGithubRepositoryHolder> getRepositoryList();
    
}
