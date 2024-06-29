package dev.kjaehyeok21.profile_website.services;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlogPostService {

    Flux<GetBlogPostHolder> getBlogPosts();

    Mono<GetBlogPostHolder> getBlogPost(Integer id);

    Mono<Integer> createBlogPost(PostBlogPostHolder newBlog);

    Mono<Integer> updateBlogPost(Integer id, PostBlogPostHolder updateBlog);

    Mono<Integer> deleteBlogPost(Integer id);
    
}
