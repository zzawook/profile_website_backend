package dev.kjaehyeok21.profile_website.controllers;

import java.net.URL;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(RESUME_DEFAULT_PATH)
    public Mono<URL> getResume() {
        return resumeService.getResume();
    }

    @RequestMapping(method = RequestMethod.POST, path = RESUME_DEFAULT_PATH)
    public Mono<URL> postResume(@RequestPart(value = "file", required = true) Mono<FilePart> filePartFlux) {
        return resumeService.uploadResume(filePartFlux);
    }
    
    // @RequestMapping(method = RequestMethod.POST, path = RESUME_DEFAULT_PATH)
    // public Mono<Void> postResume(ServerWebExchange exchange) {
    //     return exchange.getMultipartData().flatMap((MultiValueMap data) -> {
    //         System.out.println(data);

    //         return Mono.empty();
    //     });
    // }
    
}
