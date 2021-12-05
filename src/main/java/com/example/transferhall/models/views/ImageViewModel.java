package com.example.transferhall.models.views;

public class ImageViewModel {
    private String imageDescription;
    private String altTag;
    private String imageFile;
    private String videoLink;
    private String publicId;
    private String category;


    public String getImageDescription() {
        return imageDescription;
    }

    public ImageViewModel setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
        return this;
    }

    public String getAltTag() {
        return altTag;
    }

    public ImageViewModel setAltTag(String altTag) {
        this.altTag = altTag;
        return this;
    }

    public String getImageFile() {
        return imageFile;
    }

    public ImageViewModel setImageFile(String imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public ImageViewModel setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public ImageViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ImageViewModel setCategory(String category) {
        this.category = category;
        return this;
    }
}
