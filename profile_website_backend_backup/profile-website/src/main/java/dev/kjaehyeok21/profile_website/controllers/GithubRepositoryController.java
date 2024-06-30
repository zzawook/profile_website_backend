package dev.kjaehyeok21.profile_website.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import dev.kjaehyeok21.profile_website.services.GithubRepositoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GithubRepositoryController {

    public static final String GITHUB_REPOSITORY_DEFAULT_PATH = "/api/v1/repos";

    @Autowired
    private GithubRepositoryService repoService;
    
    @GetMapping(GITHUB_REPOSITORY_DEFAULT_PATH)
    public List<GetGithubRepositoryHolder> getRepositoryList() {
        return repoService.getRepositoryList();
    }
    
}
