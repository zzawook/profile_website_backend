package dev.kjaehyeok21.profile_website.controllers;

import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.kjaehyeok21.profile_website.services.ResumeService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
public class ResumeController {
    
    private final String RESUME_DEFAULT_PATH = "/api/v1/resume";

    private final ResumeService resumeService;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @GetMapping(RESUME_DEFAULT_PATH)
    public Mono<URL> getResume() {
        return resumeService.getResume();
    }

    @PostMapping(RESUME_DEFAULT_PATH)
    public Mono<URL> postResume(@RequestBody MultipartFile newResumeFile) {
        return resumeService.uploadResume(newResumeFile);
    }
    
    
}
