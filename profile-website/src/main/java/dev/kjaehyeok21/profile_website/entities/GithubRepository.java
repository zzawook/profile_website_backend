package dev.kjaehyeok21.profile_website.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
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

    @org.springframework.data.annotation.Id
    private UUID id;

    @NotNull
    @NotBlank
    private String repoName;

    @org.hibernate.validator.constraints.URL
    private String repoUrl;
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
