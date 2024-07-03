package dev.kjaehyeok21.profile_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProfileWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileWebsiteApplication.class, args);
	}

}
