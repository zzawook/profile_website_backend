package dev.kjaehyeok21.profile_website.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.kjaehyeok21.profile_website.entities.BlogPost;
import dev.kjaehyeok21.profile_website.models.GetBlogPostHolder;

@Mapper
public interface BlogPostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mediumId", ignore = true)
    BlogPost getBlogHolderToBlogPost(GetBlogPostHolder getBlogHolder);

    GetBlogPostHolder blogPostToGetBlogHolder(BlogPost blogPost);
}
