package dev.kjaehyeok21.profile_website.services;

import java.net.URL;

import org.springframework.http.codec.multipart.FilePart;

import reactor.core.publisher.Mono;

public interface ResumeService {

    Mono<URL> getResume();

    Mono<URL> uploadResume(Mono<FilePart> fileParMono);

}
