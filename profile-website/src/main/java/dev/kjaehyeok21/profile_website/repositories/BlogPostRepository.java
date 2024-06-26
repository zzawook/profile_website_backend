package dev.kjaehyeok21.profile_website.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import dev.kjaehyeok21.profile_website.entities.BlogPost;

public interface BlogPostRepository extends ReactiveCrudRepository<BlogPost, UUID>{
    
}
