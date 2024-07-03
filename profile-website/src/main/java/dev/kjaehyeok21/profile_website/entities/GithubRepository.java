package dev.kjaehyeok21.profile_website.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@EnableRedisRepositories
@RedisHash
public class GithubRepository {

    @Id
    private Integer id;

    @NotNull
    @NotBlank
    private String repoName;

    @org.hibernate.validator.constraints.URL
    private String repoUrl;
    private String description;

    private String language;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
