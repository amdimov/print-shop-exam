package com.example.transferhall.models.bindingModels.admin.binding;

import com.example.transferhall.util.validator.FileIsNotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UploadImageBinding {
    private String imageDescription;
    private String altTag;
    @FileIsNotEmpty
    private MultipartFile imageFile;
    private String videoLink;
    private String category;


    public String getImageDescription() {
        return imageDescription;
    }

    public UploadImageBinding setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
        return this;
    }

    public String getAltTag() {
        return altTag;
    }

    public UploadImageBinding setAltTag(String altTag) {
        this.altTag = altTag;
        return this;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public UploadImageBinding setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public UploadImageBinding setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public UploadImageBinding setCategory(String category) {
        this.category = category;
        return this;
    }
}
