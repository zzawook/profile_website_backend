package dev.kjaehyeok21.profile_website.services;

import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;


@Service
public class AwsSecretsManagerServiceImpl implements AwsSecretsManagerService {

    private final String secretName = "/kjaehyeok21_profile_website/spring_secrets";

    private final Map<String, Object> secretObj;

    public AwsSecretsManagerServiceImpl(SecretsManagerClient awsSecretsManagerClient) {
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build();

        GetSecretValueResponse getSecretValueResponse = awsSecretsManagerClient.getSecretValue(getSecretValueRequest);

        String secretString = getSecretValueResponse.secretString();

        JsonParser jsonParser = new BasicJsonParser();
        this.secretObj = jsonParser.parseMap(secretString);
    }

    @Override
    public String getSecret(String secretName) {
        if (this.secretObj.containsKey(secretName)) {
            return (String) this.secretObj.get(secretName);
        } else {
            return "Secret not found.";
        }
    }
    
}
