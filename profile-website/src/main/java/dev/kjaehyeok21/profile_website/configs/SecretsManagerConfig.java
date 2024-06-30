package dev.kjaehyeok21.profile_website.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
@RequiredArgsConstructor
public class SecretsManagerConfig {

    Region region = Region.of("ap-southeast-1");

    private final AwsCredentials awsCredentials;

    // Create a Secrets Manager client
    @Bean
    SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
            .region(region)
            .credentialsProvider(() -> awsCredentials)
            .build();
    }
}
