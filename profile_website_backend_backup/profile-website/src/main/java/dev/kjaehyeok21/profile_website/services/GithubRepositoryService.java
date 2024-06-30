package dev.kjaehyeok21.profile_website.services;

import java.util.List;

import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;

public interface GithubRepositoryService {

    List<GetGithubRepositoryHolder> getRepositoryList();
    
}
