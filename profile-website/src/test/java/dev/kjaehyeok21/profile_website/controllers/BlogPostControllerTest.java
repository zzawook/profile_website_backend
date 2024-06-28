package dev.kjaehyeok21.profile_website.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;
import dev.kjaehyeok21.profile_website.services.BlogPostService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class BlogPostControllerTest {

    @Mock
    private BlogPostService blogPostService;

    @InjectMocks
    private BlogPostController blogPostController;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBlogPosts() {
        // Arrange
        when(blogPostService.getBlogPosts()).thenReturn(Flux.empty());

        // Act
        Flux<GetBlogPostHolder> result = blogPostController.getBlogPosts("param");

        // Assert
        // Add your assertions here
    }

    @Test
    public void testGetBlogPost() {
        // Arrange
        UUID postId = UUID.randomUUID();
        when(blogPostService.getBlogPost(eq(postId))).thenReturn(Mono.empty());

        // Act
        Mono<GetBlogPostHolder> result = blogPostController.getBlogPost(postId);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testPostBlogPost() {
        // Arrange
        PostBlogPostHolder newBlog = new PostBlogPostHolder();

        // Act
        Mono<ResponseEntity<Void>> result = blogPostController.postBlogPost(newBlog);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testUpdateBlogPost() {
        // Arrange
        UUID postId = UUID.randomUUID();
        PostBlogPostHolder updateBlog = new PostBlogPostHolder();

        // Act
        Mono<ResponseEntity<Void>> result = blogPostController.updateBlogPost(postId, updateBlog);

        // Assert
        // Add your assertions here
    }

    @Test
    public void testDeleteBlogPost() {
        // Arrange
        UUID postId = UUID.randomUUID();

        // Act
        Mono<ResponseEntity<Void>> result = blogPostController.deleteBlogPost(postId);

        // Assert
        // Add your assertions here
    }
}