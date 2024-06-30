package dev.kjaehyeok21.profile_website.services;

public interface AwsSecretsManagerService {
    
    String getSecret(String secretName) throws Exception;;
}
