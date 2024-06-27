package dev.kjaehyeok21.profile_website.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.kjaehyeok21.profile_website.models.GetGithubRepositoryHolder;
import dev.kjaehyeok21.profile_website.services.GithubRepositoryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GithubRepositoryController {

    private final String GITHUB_REPOSITORY_DEFAULT_PATH = "/api/v1/repos";

    private GithubRepositoryService repoService;
    
    @GetMapping(GITHUB_REPOSITORY_DEFAULT_PATH)
    public List<GetGithubRepositoryHolder> getRepositoryList(@RequestParam String param) {
        return repoService.getRepositoryList();
    }
    
}
