package dev.kjaehyeok21.profile_website.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${spring.cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String privateKey;

    @Value("${spring.cloud.aws.s3.region}")
    private String region;

    @Bean
    S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(() -> this.awsCredentials())
                .region(Region.of(region))
                .build();
    }

    AwsCredentials awsCredentials() {
        return new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return accessKey;
            }

            @Override
            public String secretAccessKey() {
                return privateKey;
            }
        };
    }
}
