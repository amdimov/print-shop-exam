package com.example.transferhall.googleDrive;

import com.example.transferhall.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class DriveEntity extends BaseEntity {
    private String publicId;
    private String url;

    public String getPublicId() {
        return publicId;
    }

    public DriveEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DriveEntity setUrl(String url) {
        this.url = url;
        return this;
    }
}
