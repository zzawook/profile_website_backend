package dev.kjaehyeok21.profile_website.services;

import java.net.URL;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Override
    public Mono<URL> getResume() {
        return Mono.empty();
    }
    
}
