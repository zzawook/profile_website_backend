package dev.kjaehyeok21.profile_website.entities;

import java.net.URL;

import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

@Component
public class Resume {

    S3Client s3Client;

    private final String bucket = "kjaehyeok21";

    URL url;

    public Resume(S3Client s3Client) {
        this.s3Client = s3Client;
        this.url = this.getResumeUrl();
    }

    private URL getResumeUrl() {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket)
            .key("resume.pdf")
            .build();

        return s3Client.utilities().getUrl(getUrlRequest);
    }

    public URL getURL() {
        return this.url;
    }

    public void setURL(URL newUrl) {
        this.url = newUrl;
    }
}
