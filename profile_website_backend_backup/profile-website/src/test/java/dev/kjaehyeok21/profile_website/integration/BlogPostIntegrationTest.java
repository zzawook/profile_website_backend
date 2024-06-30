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
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;

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
    
    @Test
    public void testGetBlogPost() {
        webTestClient.get().uri(BlogPostController.BLOG_POST_ID_PATH, 1)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetBlogPostWithInvalidParamReturnsNotFound() {
        // Act
        webTestClient.get().uri(BlogPostController.BLOG_POST_ID_PATH, Integer.MAX_VALUE)
                .exchange()
                .expectStatus().isNotFound();

        // Assert
        // Add your assertions here
    }

    @Test
    public void testPostBlogPost() {
        // Arrange
        PostBlogPostHolder newBlog = PostBlogPostHolder.builder()
            .title("Sample Title")
            .markdownContent("new markdown content")
            .build();

        // Act
        webTestClient.post().uri(BlogPostController.BLOG_POST_DEFAULT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newBlog)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void testPostBlogPostWithInvalidContentReturnsBadRequest() {
        // Arrange
        PostBlogPostHolder newBlogTitleNull = PostBlogPostHolder.builder()
            .title(null)
            .markdownContent("new markdown content")
            .build();

        PostBlogPostHolder newBlogTitleTooLong = PostBlogPostHolder.builder()
            .title("a".repeat(513))
            .markdownContent("new markdown content")
            .build();

        // Act
        webTestClient.post().uri(BlogPostController.BLOG_POST_DEFAULT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newBlogTitleNull)
                .exchange()
                .expectStatus().isBadRequest();

        webTestClient.post().uri(BlogPostController.BLOG_POST_DEFAULT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newBlogTitleTooLong)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testUpdateBlogPost() {
        // Arrange
        PostBlogPostHolder updateBlog = PostBlogPostHolder.builder()
            .title("Updated Title")
            .markdownContent("updated markdown content")
            .build();

        // Act
        webTestClient.put().uri(BlogPostController.BLOG_POST_ID_PATH, 1)
                .bodyValue(updateBlog)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testUpdateBlogPostWithInvalidParamReturnsNotFound() {
        // Arrange
        PostBlogPostHolder updateBlog = PostBlogPostHolder.builder()
            .title("Updated Title")
            .markdownContent("updated markdown content")
            .build();

        // Act
        webTestClient.put().uri(BlogPostController.BLOG_POST_ID_PATH, Integer.MAX_VALUE)
                .bodyValue(updateBlog)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testDeleteBlogPost() {
        webTestClient.delete().uri(BlogPostController.BLOG_POST_ID_PATH, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testDeleteBlogPostWithInvalidParamReturnsNotFound() {
        webTestClient.delete().uri(BlogPostController.BLOG_POST_ID_PATH, Integer.MAX_VALUE)
                .exchange()
                .expectStatus().isNotFound();
    }
}