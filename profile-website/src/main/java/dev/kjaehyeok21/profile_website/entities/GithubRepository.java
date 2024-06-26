package dev.kjaehyeok21.profile_website.entities;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@EnableR2dbcAuditing
@Entity
public class GithubRepository {

    @Id
    private UUID id;

    @NotNull
    @NotBlank
    private String repoName;

    @org.hibernate.validator.constraints.URL
    private URL repoUrl;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
