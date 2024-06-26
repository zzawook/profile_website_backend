package dev.kjaehyeok21.profile_website.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    private Region region = Region.of("ap-southeast-1");

    private final AwsCredentials awsCredentials;

    @Bean
    S3Client s3Client(){
        return S3Client.builder()
                .credentialsProvider(() -> awsCredentials)
                .region(region)
                .build();
    }

    
}
