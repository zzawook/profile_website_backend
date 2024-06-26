package dev.kjaehyeok21.profile_website.services;

import java.util.UUID;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogPostService {

    Flux<GetBlogPostHolder> getBlogPosts();

    Mono<GetBlogPostHolder> getBlogPost(UUID id);

    Mono<UUID> createBlogPost(PostBlogPostHolder newBlog);

    Mono<UUID> updateBlogPost(UUID id, PostBlogPostHolder updateBlog);

    Mono<UUID> deleteBlogPost(UUID id);
    
}
