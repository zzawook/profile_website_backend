package dev.kjaehyeok21.profile_website.services;

import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import reactor.core.publisher.Flux;

public interface BlogPostService {

    Flux<GetBlogPostHolder> getBlogPosts();
    
}
