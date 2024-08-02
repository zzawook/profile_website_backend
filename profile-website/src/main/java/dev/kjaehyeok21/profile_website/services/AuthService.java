package dev.kjaehyeok21.profile_website.services;

import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<Boolean> authByPassword(String password);
    
}
