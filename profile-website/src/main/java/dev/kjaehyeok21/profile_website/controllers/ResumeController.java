package dev.kjaehyeok21.profile_website.controllers;

import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kjaehyeok21.profile_website.services.AuthService;
import dev.kjaehyeok21.profile_website.services.ResumeService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequiredArgsConstructor
public class ResumeController {
    
    private final String RESUME_DEFAULT_PATH = "/api/v1/resume";

    private final ResumeService resumeService;

    private final AuthService authService;

    @GetMapping(RESUME_DEFAULT_PATH)
    public Mono<URL> getResume() {
        return resumeService.getResume();
    }

    @RequestMapping(method = RequestMethod.POST, path = RESUME_DEFAULT_PATH)
    public Mono<ResponseEntity<String>> postResume(@RequestPart(value = "file", required = true) Mono<FilePart> filePartFlux, @RequestPart(value = "password", required = true) String password) {
        return authService.authByPassword(password).flatMap(authResult -> {
            if (!authResult) {
                return Mono.just(new ResponseEntity<String>("Not Authenticated", org.springframework.http.HttpStatus.UNAUTHORIZED));
            }
            return resumeService.uploadResume(filePartFlux).map(url -> {
                return new ResponseEntity<>(url.toString(), org.springframework.http.HttpStatus.OK);
            });
        });
        
    }
}
