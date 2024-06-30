package dev.kjaehyeok21.profile_website.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import dev.kjaehyeok21.profile_website.services.AwsSecretsManagerService;


@Configuration
@EnableRedisRepositories
public class RedisConfig {

    private String host;

    private int port;

    private String username;

    private String password;

    public RedisConfig(AwsSecretsManagerService awsSecretsManagerService) {
        try {
            this.host = awsSecretsManagerService.getSecret("redis_host");
            this.port = Integer.parseInt(awsSecretsManagerService.getSecret("redis_port"));
            this.username = awsSecretsManagerService.getSecret("redis_client-name");
            this.password = awsSecretsManagerService.getSecret("redis_password");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setUsername(username);
        redisStandaloneConfiguration.setPassword(password);
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        
        return jedisConFactory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
