package dev.kjaehyeok21.profile_website.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {
    public Mono<Boolean> authByPassword(String password) {
        return Mono.just(password.equals("password"));
    }
    
}
