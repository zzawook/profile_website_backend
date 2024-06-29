package dev.kjaehyeok21.profile_website.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

import dev.kjaehyeok21.profile_website.controllers.GithubRepositoryController;
import dev.kjaehyeok21.profile_website.services.GithubRepositoryService;

@AutoConfigureWebTestClient
@SpringBootTest
public class GithubRepositoryIntegrationTest {

    @MockBean
    private GithubRepositoryService repoService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testGetRepositoryList() throws Exception {
        webTestClient.get().uri(GithubRepositoryController.GITHUB_REPOSITORY_DEFAULT_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$").isArray();
    }

}
