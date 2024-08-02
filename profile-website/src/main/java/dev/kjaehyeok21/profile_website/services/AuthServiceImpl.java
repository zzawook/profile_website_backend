package dev.kjaehyeok21.profile_website.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

    private String secret = "";

    public AuthServiceImpl(AwsSecretsManagerService awsSecretsManagerService) {
        try {
            this.secret = awsSecretsManagerService.getSecret("admin_password");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching Auth Password");
            return;
        }
    }

    public Mono<Boolean> authByPassword(String password) {
        return Mono.just(password.equals(this.secret));
    }
    
}
