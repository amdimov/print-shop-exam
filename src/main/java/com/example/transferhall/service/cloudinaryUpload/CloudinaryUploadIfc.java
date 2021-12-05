package com.example.transferhall.service.cloudinaryUpload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryUploadIfc {
    CloudinaryFile addFileToCloudinary(MultipartFile orderFile) throws IOException;
    boolean deleteFileFromCloudinary(String publicId);
}
