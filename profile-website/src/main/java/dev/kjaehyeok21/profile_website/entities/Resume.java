package dev.kjaehyeok21.profile_website.entities;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

@Component
public class Resume {

    @Autowired
    S3Client s3client;

    @Value("S${spring.cloud.aws.s3.bucket}")
    private String bucket;

    URL url;

    public Resume() {
        this.url = this.getResumeUrl();
    }

    private URL getResumeUrl() {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket)
            .key("resume.pdf")
            .build();

        return s3client.utilities().getUrl(getUrlRequest);
    }

    public URL getURL() {
        return this.url;
    }

    public void setURL(URL newUrl) {
        this.url = newUrl;
    }
}
