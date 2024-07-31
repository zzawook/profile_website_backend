package dev.kjaehyeok21.profile_website.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import dev.kjaehyeok21.profile_website.entities.GithubRepository;
import dev.kjaehyeok21.profile_website.repositories.BlogPostRepository;
import dev.kjaehyeok21.profile_website.repositories.GitHubRepositoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class InjectTestData implements CommandLineRunner {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private GitHubRepositoryRepository gitHubRepositoryRepository;

    @Override
    public void run(String... args) throws Exception {
        populateBlogPostData();
        populateGithubRepoData();
    }

    private void populateGithubRepoData() {
        GithubRepository githubRepository1 = GithubRepository.builder()
                .repoName("SAMPEL REPO 1")
                .description("TeST REPO 1")
                .repoUrl("https://github.com/zzawook/profile_website_backend")
                .build();

        GithubRepository githubRepository2 = GithubRepository.builder()
                .repoName("SAMPEL REPO 2")
                .description("Test REPO 2")
                .repoUrl("https://github.com/zzawook/profile_website")
                .build();

        gitHubRepositoryRepository.save(githubRepository1);
        gitHubRepositoryRepository.save(githubRepository2);
    }

    private void populateBlogPostData() {
        BlogPost blogPost1 = BlogPost.builder()
                .subtitle("SAMPLE MARK DOWN CONTENT1")
                .title("SAMPLE BLOG POST 1")
                .url("SOMEURL1")
                .build();

        BlogPost blogPost2 = BlogPost.builder()
                .title("SAMPLE BLOGG POST 2")
                .subtitle("SAMPLE MARK DOWN CONTENT2")
                .url("SOMEURL2")
                .build();

        blogPostRepository.save(blogPost1).subscribe();
        blogPostRepository.save(blogPost2).subscribe();
    }

}
