package dev.kjaehyeok21.profile_website.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.kjaehyeok21.profile_website.services.AuthService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    public static final String AUTH_DEFAULT_PATH = "/api/v1/auth";

    @PostMapping(AUTH_DEFAULT_PATH)
    public Mono<ResponseEntity<String>> authByPassword(@RequestBody String password) {
        return authService.authByPassword(password).map(authResult -> {
            if (authResult) {
                return new ResponseEntity<>("Authenticated", org.springframework.http.HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Authenticated", org.springframework.http.HttpStatus.UNAUTHORIZED);
            }
        });
    }
    
}
