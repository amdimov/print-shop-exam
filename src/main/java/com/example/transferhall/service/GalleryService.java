package com.example.transferhall.service;

import com.example.transferhall.models.views.ImageViewModel;
import com.example.transferhall.models.bindingModels.admin.binding.UploadImageBinding;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Optional;

public interface GalleryService {
    Optional<ImageViewModel> uploadImage(UploadImageBinding uploadBinding) throws IOException;
    void deleteImage(String publicId) throws IOException;
    Page<ImageViewModel> fetchPageableImages(Integer pageNo, Integer pageSize, String sortBy);
}
