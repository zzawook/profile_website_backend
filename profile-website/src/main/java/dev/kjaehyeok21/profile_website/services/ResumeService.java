package dev.kjaehyeok21.profile_website.services;

import java.net.URL;

import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Mono;

public interface ResumeService {

    Mono<URL> getResume();

    Mono<URL> uploadResume(MultipartFile newResumeFile);

}
