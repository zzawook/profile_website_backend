package dev.kjaehyeok21.profile_website.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import dev.kjaehyeok21.profile_website.mappers.BlogPostMapper;
import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;
import dev.kjaehyeok21.profile_website.repositories.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService{

    BlogPostRepository blogPostRepository;
    BlogPostMapper blogPostMapper;

    @Override
    public Flux<GetBlogPostHolder> getBlogPosts() {
        return blogPostRepository.findAll().map(blogPost -> blogPostMapper.blogPostToGetBlogHolder(blogPost));
    }

    @Override
    public Mono<GetBlogPostHolder> getBlogPost(UUID id) {
        return blogPostRepository.findById(id).map(blogPost -> blogPostMapper.blogPostToGetBlogHolder(blogPost));
    }

    @Override
    public Mono<UUID> createBlogPost(PostBlogPostHolder newBlog) {
        Mono<BlogPost> addedBlogPost = blogPostRepository.save(blogPostMapper.postBlogHolderToBlogPost(newBlog));

        return addedBlogPost.map(blogPost -> blogPost.getId());
    }

    @Override
    public Mono<UUID> updateBlogPost(UUID id, PostBlogPostHolder updateBlog) {
        Mono<BlogPost> blogPostToUpdate = blogPostRepository.findById(id);

        return blogPostToUpdate.map(blogPost -> {
            blogPost.setTitle(updateBlog.getTitle());
            blogPost.setMarkdownContent(updateBlog.getMarkdownContent());

            return blogPost;
        }).flatMap(blogPost -> blogPostRepository.save(blogPost)).map(blogPost -> blogPost.getId());
    }

    @Override
    public Mono<UUID> deleteBlogPost(UUID id) {
        Mono<BlogPost> blogPostToDelete = blogPostRepository.findById(id);

        return blogPostToDelete.map(blogPost -> blogPost.getId());
    }
    
}
