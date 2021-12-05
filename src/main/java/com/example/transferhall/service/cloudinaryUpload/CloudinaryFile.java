package com.example.transferhall.service.cloudinaryUpload;

public class CloudinaryFile {
    private String url;
    private String publicId;

    public CloudinaryFile(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public CloudinaryFile setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public CloudinaryFile setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
