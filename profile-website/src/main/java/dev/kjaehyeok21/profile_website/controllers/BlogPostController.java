package dev.kjaehyeok21.profile_website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.services.BlogPostService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogPostController {
    
    @Autowired
    BlogPostService blogPostService;

    @Value("${mydomain}")
    private String domain;

    public static final String BLOG_POST_DEFAULT_PATH = "/api/v1/blogPost";
    public static final String BLOG_POST_ID_PATH = BLOG_POST_DEFAULT_PATH + "/{postId}";


    @GetMapping(BLOG_POST_DEFAULT_PATH)
    public Flux<GetBlogPostHolder> getBlogPosts() {
        return blogPostService.getBlogPosts();
    }
}
