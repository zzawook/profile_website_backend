package dev.kjaehyeok21.profile_website.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;
import dev.kjaehyeok21.profile_website.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequiredArgsConstructor
public class BlogPostController {
    
    BlogPostService blogPostService;

    private final String BLOG_POST_DEFAULT_PATH = "/api/v1/blogPost";
    private final String BLOG_POST_ID_PATH = BLOG_POST_DEFAULT_PATH + "/{postId}";


    @GetMapping(BLOG_POST_DEFAULT_PATH)
    public Flux<GetBlogPostHolder> getBlogPosts(@RequestParam String param) {
        return blogPostService.getBlogPosts();
    }

    @GetMapping(BLOG_POST_ID_PATH)
    public Mono<GetBlogPostHolder> getBlogPost(@PathVariable("postId") UUID id) {
        return blogPostService.getBlogPost(id).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
    
    @PostMapping(BLOG_POST_DEFAULT_PATH)
    public Mono<ResponseEntity<Void>> postBlogPost(@Validated @RequestBody PostBlogPostHolder newBlog) {
        Mono<UUID> postedPostId = blogPostService.createBlogPost(newBlog);
        
        return postedPostId.map(uuid -> ResponseEntity.created(UriComponentsBuilder.fromHttpUrl(BLOG_POST_DEFAULT_PATH).build().toUri()).build());
    }
    
    @PutMapping(BLOG_POST_ID_PATH)
    public Mono<ResponseEntity<Void>> updateBlogPost(@PathVariable UUID id, @Validated @RequestBody PostBlogPostHolder updateBlog) {
        Mono<UUID> updatedPostId = blogPostService.updateBlogPost(id, updateBlog);
        
        return updatedPostId.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))).map(uuid -> ResponseEntity.noContent().build());
    }
    
    @DeleteMapping(BLOG_POST_ID_PATH)
    public Mono<ResponseEntity<Void>> deleteBlogPost(@PathVariable UUID id) {
        Mono<UUID> deletedPostId = blogPostService.deleteBlogPost(id);

        return deletedPostId.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))).map(uuid -> ResponseEntity.noContent().build());
    }
}
