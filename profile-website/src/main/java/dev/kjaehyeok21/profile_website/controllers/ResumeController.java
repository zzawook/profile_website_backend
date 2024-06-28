package dev.kjaehyeok21.profile_website.controllers;

import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.kjaehyeok21.profile_website.services.ResumeService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Controller
@RequiredArgsConstructor
public class ResumeController {
    
    private final String RESUME_DEFAULT_PATH = "/api/v1/resume";

    private final ResumeService resumeService;

    @GetMapping(RESUME_DEFAULT_PATH)
    public Mono<URL> getResume() {
        return resumeService.getResume();
    }
    
}
