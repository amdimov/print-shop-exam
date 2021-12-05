package com.example.transferhall.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.transferhall.models.GalleryEntity;
import com.example.transferhall.models.views.ImageViewModel;
import com.example.transferhall.models.bindingModels.admin.binding.UploadImageBinding;
import com.example.transferhall.models.enums.TransferCategoryEnum;
import com.example.transferhall.repository.GalleryRepository;
import com.example.transferhall.service.GalleryService;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryFile;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryUploadIfc;
import com.example.transferhall.util.exceptions.InvalidFileFormat;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl implements GalleryService {
    private final CloudinaryUploadIfc cloudinaryUpload;
    private final GalleryRepository galleryRepository;
    private final ModelMapper modelMapper;

    public GalleryServiceImpl(CloudinaryUploadIfc cloudinaryUpload,
                              GalleryRepository galleryRepository,
                              ModelMapper modelMapper) {
        this.cloudinaryUpload = cloudinaryUpload;
        this.galleryRepository = galleryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<ImageViewModel> uploadImage(UploadImageBinding uploadBinding)
            throws IOException {
        MultipartFile multipartFile = uploadBinding.getImageFile();

        if (multipartFile == null) {
            throw new InvalidFileFormat("File should be png or jpeg");
        }

        CloudinaryFile cloudinaryFile = cloudinaryUpload.addFileToCloudinary(multipartFile);
        GalleryEntity galleryEntity = new GalleryEntity()
                .setAltTag(uploadBinding.getAltTag())
                .setImageDescription(uploadBinding.getImageDescription())
                .setImageFile(cloudinaryFile.getUrl())
                .setPublicId(cloudinaryFile.getPublicId())
                .setVideoLink(uploadBinding.getVideoLink())
                .setCreated(LocalDate.now())
                .setCategory(TransferCategoryEnum.valueOf(uploadBinding.getCategory()));
        galleryRepository.save(galleryEntity);

        ImageViewModel view = modelMapper.map(galleryEntity, ImageViewModel.class);
        return Optional.of(view);
    }

    @Override
    public boolean deleteImage(String publicId) throws IOException {
        if (!galleryRepository.findByPublicId(publicId).isPresent()) {
            return false;
        }
        galleryRepository.deleteGalleryEntitiesByPublicId(publicId);
        cloudinaryUpload.deleteFileFromCloudinary(publicId);
        return true;
    }

    @Override
    @Cacheable("images")
    public List<ImageViewModel> fetchAllImages() {
        return
                galleryRepository.findAll()
                        .stream()
                        .map(e -> modelMapper.map(e, ImageViewModel.class))
                        .collect(Collectors.toList());
    }

    @Override
    public Page<ImageViewModel> fetchPageableImages(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return galleryRepository.findAll(pageable)
                .map(e -> modelMapper.map(e, ImageViewModel.class));
    }

}
