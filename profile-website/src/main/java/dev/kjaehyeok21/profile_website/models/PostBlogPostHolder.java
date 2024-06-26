package dev.kjaehyeok21.profile_website.models;

import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EnableR2dbcAuditing
public class PostBlogPostHolder {
    @NotNull
    @NotBlank
    @Size(max = 512)
    private String title;
    private String markdownContent;
}
