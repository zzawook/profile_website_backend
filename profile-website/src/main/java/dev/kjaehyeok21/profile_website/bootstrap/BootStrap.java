package dev.kjaehyeok21.profile_website.bootstrap;

import java.net.URL;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class BootStrap implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        populateGithubRepoData();
        Mono<URL> resumeUrlMono = getResumeUrl();
    }

    private Mono<URL> getResumeUrl() {
        return Mono.empty();
    }

    private void populateGithubRepoData() {

    }
}
