package dev.kjaehyeok21.profile_website.models;

import org.springframework.web.multipart.MultipartFile;

public class Payload {
    private MultipartFile file;

    public Payload(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
