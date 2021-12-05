package com.example.transferhall.service.cloudinaryUpload;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryUpload implements CloudinaryUploadIfc{
    private final Cloudinary cloudinary;
    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";


    public CloudinaryUpload(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CloudinaryFile addFileToCloudinary(MultipartFile orderFile) throws IOException {
        File tempFile = File
                .createTempFile(TEMP_FILE, orderFile.getOriginalFilename());
        orderFile.transferTo(tempFile);
        try {
            Map<String, String> upload = cloudinary.uploader().upload(tempFile, Map.of());
            String url = upload.getOrDefault(URL, "https://i.stack.imgur.com/6M513.png");
            String publicId = upload.getOrDefault(PUBLIC_ID, "");
            return new CloudinaryFile(url, publicId);
        }finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean deleteFileFromCloudinary(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
