package dev.kjaehyeok21.profile_website.configs;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import dev.kjaehyeok21.profile_website.services.AwsSecretsManagerService;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

@Configuration
public class MysqlConfig {
    
    @Value("classpath:/schema.sql")
    Resource resource;

    private String username;

    private String password;

    private String dbName;

    @Value("${mysql.endpoint}")
    private String mysqlEndpoint;

    public MysqlConfig(AwsSecretsManagerService awsSecretsManagerService) {
        try {
            this.username = awsSecretsManagerService.getSecret("mysql_username");
            this.password = awsSecretsManagerService.getSecret("mysql_password");
            this.dbName = awsSecretsManagerService.getSecret("mysql_db_name");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return ConnectionFactoryBuilder.withOptions(
                ConnectionFactoryOptions.builder()
                        .option(ConnectionFactoryOptions.DRIVER, "mysql")
                        .option(ConnectionFactoryOptions.HOST, mysqlEndpoint)
                        .option(ConnectionFactoryOptions.USER, username)
                        .option(ConnectionFactoryOptions.PASSWORD, password)
                        .option(ConnectionFactoryOptions.DATABASE, dbName)
                        .option(ConnectionFactoryOptions.CONNECT_TIMEOUT, Duration.ofSeconds(10)))
                .build();
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

        initializer.setConnectionFactory(connectionFactory);

        initializer.setDatabasePopulator(new ResourceDatabasePopulator(resource));

        return initializer;
    }
}
