package com.example.transferhall.models;

import com.example.transferhall.models.enums.TransferCategoryEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "gallery")
public class GalleryEntity extends BaseEntity{
    private String imageDescription;
    private String altTag;
    private String imageFile;
    private String videoLink;
    private String publicId;
    private LocalDate created;
    @Enumerated(EnumType.STRING)
    private TransferCategoryEnum category;


    public String getImageDescription() {
        return imageDescription;
    }

    public GalleryEntity setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
        return this;
    }

    public String getAltTag() {
        return altTag;
    }

    public GalleryEntity setAltTag(String altTag) {
        this.altTag = altTag;
        return this;
    }

    public String getImageFile() {
        return imageFile;
    }

    public GalleryEntity setImageFile(String imageFile) {
        this.imageFile = imageFile;
        return this;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public GalleryEntity setVideoLink(String videoLink) {
        this.videoLink = videoLink;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public GalleryEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public GalleryEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public TransferCategoryEnum getCategory() {
        return category;
    }

    public GalleryEntity setCategory(TransferCategoryEnum category) {
        this.category = category;
        return this;
    }
}
