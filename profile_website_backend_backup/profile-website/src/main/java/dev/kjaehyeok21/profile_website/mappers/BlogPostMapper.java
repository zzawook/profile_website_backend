package dev.kjaehyeok21.profile_website.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;
import dev.kjaehyeok21.profile_website.models.PostBlogPostHolder;

@Mapper
public interface BlogPostMapper {

    @Mapping(target = "id", ignore = true)
    BlogPost getBlogHolderToBlogPost(GetBlogPostHolder getBlogHolder);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    BlogPost postBlogHolderToBlogPost(PostBlogPostHolder postBlogHolder);

    PostBlogPostHolder blogPostToPostBlogHolder(BlogPost blogPost);

    GetBlogPostHolder blogPostToGetBlogHolder(BlogPost blogPost);
}
