package dev.kjaehyeok21.profile_website.services;

import java.net.URL;

import reactor.core.publisher.Mono;

public interface ResumeService {

    Mono<URL> getResume();

}
