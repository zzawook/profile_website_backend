package dev.kjaehyeok21.profile_website.services;

import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import dev.kjaehyeok21.profile_website.entities.Resume;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    Resume resume;

    S3Client s3Client;

    private final String bucket = "kjaehyeok21";

    private final String fileName = "resume.pdf";

    private URL resumeUrl;

    public ResumeServiceImpl(Resume resume, S3Client client) {
        this.resume = resume;
        this.s3Client = client;

        this.resumeUrl = fetchResumeFromRds();
    }

    @Scheduled(cron = "0 0 * * * *")
    private void fetchResumeUrlHourly() {
        this.resumeUrl = fetchResumeFromRds();
    }

    private URL fetchResumeFromRds() {
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();

        return s3Client.utilities().getUrl(getUrlRequest);
    }

    @Override
    public Mono<URL> getResume() {
        return Mono.just(resume.getURL());
    }

    @Override
    public Mono<URL> uploadResume(MultipartFile newResumeFile) {
        if(newResumeFile.isEmpty()) {
            return Mono.empty();
        }

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .contentType(newResumeFile.getContentType())
                    .contentLength(newResumeFile.getSize())
                    .key(fileName)
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(newResumeFile.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.resumeUrl = fetchResumeFromRds();

        return Mono.just(this.resumeUrl);
    }
}
