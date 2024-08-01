package dev.kjaehyeok21.profile_website.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import reactor.core.publisher.Mono;

@Repository
public interface BlogPostRepository extends ReactiveCrudRepository<BlogPost, Integer>{

    Mono<BlogPost> findByMediumId(String articleId);
    
}
