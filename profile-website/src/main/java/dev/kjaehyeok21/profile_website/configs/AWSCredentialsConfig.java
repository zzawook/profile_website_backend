package dev.kjaehyeok21.profile_website.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

import software.amazon.awssdk.auth.credentials.AwsCredentials;

@Configuration
public class AWSCredentialsConfig {

    @Bean
    AwsCredentials awsCredentials() {
        AWSCredentials credentials = DefaultAWSCredentialsProviderChain.getInstance().getCredentials();
        return new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return credentials.getAWSAccessKeyId();
            }

            @Override
            public String secretAccessKey() {
                return credentials.getAWSSecretKey();
            }
        };
    }
}
