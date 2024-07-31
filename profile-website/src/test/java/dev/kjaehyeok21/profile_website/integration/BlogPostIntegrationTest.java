package dev.kjaehyeok21.profile_website.integration;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import dev.kjaehyeok21.profile_website.controllers.BlogPostController;

@SpringBootTest
@AutoConfigureWebTestClient
public class BlogPostIntegrationTest {

    @InjectMocks
    private BlogPostController blogPostController;

    @Autowired
    WebTestClient webTestClient;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBlogPosts() {
        // Arrange
        webTestClient.get().uri(BlogPostController.BLOG_POST_DEFAULT_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$").isArray();
    }
    
}