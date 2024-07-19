package dev.kjaehyeok21.profile_website.services;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import dev.kjaehyeok21.profile_website.entities.Resume;
import reactor.core.publisher.Mono;
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

    private final Path basePath = Paths.get(System.getProperty("user.dir"));

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
    public Mono<URL> uploadResume(Mono<FilePart> fileParMono) {
        return fileParMono.flatMap(fp -> {
            Path filePath = basePath.resolve(fp.filename());

            Mono<Void> resultMono = fp.transferTo(filePath);
            resultMono.subscribe();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, filePath);

            filePath.toFile().delete();
            return Mono.just(this.resumeUrl);
        });
    }
}
