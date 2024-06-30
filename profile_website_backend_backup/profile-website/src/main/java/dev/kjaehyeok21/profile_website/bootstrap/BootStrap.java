package dev.kjaehyeok21.profile_website.bootstrap;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

@Component
public class BootStrap implements CommandLineRunner{

    @Autowired
    S3Client s3Client;

    @Value("S${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void run(String... args) throws Exception {
        populateGithubRepoData();
        URL resumeUrl = getResumeUrl();
        
    }

    private URL getResumeUrl() {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket)
            .key("resume.pdf")
            .build();

        return s3Client.utilities().getUrl(getUrlRequest);
    }

    private void populateGithubRepoData() {

    }
}
