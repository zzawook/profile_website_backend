package dev.kjaehyeok21.profile_website.models;

import java.net.URL;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@EnableR2dbcAuditing
public class GetGithubRepositoryHolder {
    private String repoName;
    private URL repoUrl;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
