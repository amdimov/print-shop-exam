package com.example.transferhall.service.Impl;

import com.example.transferhall.models.GalleryEntity;
import com.example.transferhall.models.bindingModels.admin.binding.UploadImageBinding;
import com.example.transferhall.models.enums.TransferCategoryEnum;
import com.example.transferhall.models.views.ImageViewModel;
import com.example.transferhall.repository.GalleryRepository;
import com.example.transferhall.service.GalleryService;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryFile;
import com.example.transferhall.service.cloudinaryUpload.CloudinaryUploadIfc;
import com.example.transferhall.util.exceptions.InvalidFileFormat;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class GalleryServiceImplTest {

    private UploadImageBinding uploadImageBindingTest;
    private GalleryEntity galleryEntityTest;
    private GalleryService galleryServiceToTest;
    private ImageViewModel imageViewModelTest;

    @Mock
    private MultipartFile multipartFile;
    @Mock
    private CloudinaryUploadIfc cloudinaryUploadTest;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private GalleryRepository galleryRepository;

    private CloudinaryFile cloudinaryFileTest;

    @BeforeEach
    void init(){

        this.uploadImageBindingTest = new UploadImageBinding()
                .setImageDescription("Image Desription")
                .setImageFile(this.multipartFile)
                .setAltTag("Alt Tag")
                .setCategory(TransferCategoryEnum.SINGLESPOT.name())
                .setVideoLink("videoLink");

        this.cloudinaryFileTest = new CloudinaryFile("link", "publicId");

        this.galleryEntityTest = new GalleryEntity()
                .setAltTag(uploadImageBindingTest.getAltTag())
                .setImageDescription(uploadImageBindingTest.getImageDescription())
                .setImageFile(cloudinaryFileTest.getUrl())
                .setPublicId(cloudinaryFileTest.getPublicId())
                .setVideoLink("videoLink");

        this.galleryServiceToTest =
                new GalleryServiceImpl(cloudinaryUploadTest, galleryRepository, mockModelMapper);
        this.imageViewModelTest = new ImageViewModel()
                .setAltTag("Alt Tag")
                .setImageDescription(uploadImageBindingTest.getImageDescription())
                .setImageFile(cloudinaryFileTest.getUrl())
                .setPublicId(cloudinaryFileTest.getPublicId())
                .setVideoLink("videoLink")
                .setCategory(uploadImageBindingTest.getCategory());

    }

    @Test
    void multipartFileIsEmpty(){
        uploadImageBindingTest.setImageFile(null);
        Assertions.assertThrows(
                InvalidFileFormat.class,
                ()->this.galleryServiceToTest.uploadImage(uploadImageBindingTest)
        );
        uploadImageBindingTest.setImageFile(this.multipartFile);
    }
    @Test
    void uploadImageTest() throws IOException {
        UploadImageBinding uploadImageBinding = new UploadImageBinding()
                .setImageDescription("Image Desription")
                .setImageFile(this.multipartFile)
                .setAltTag("Alt Tag")
                .setCategory(TransferCategoryEnum.SINGLESPOT.name())
                .setVideoLink("videoLink");
        GalleryEntity galleryEntity = new GalleryEntity()
                .setAltTag(uploadImageBinding.getAltTag())
                .setImageDescription(uploadImageBinding.getImageDescription())
                .setImageFile(cloudinaryFileTest.getUrl())
                .setPublicId(cloudinaryFileTest.getPublicId())
                .setVideoLink("videoLink");

        Mockito.when(cloudinaryUploadTest.addFileToCloudinary(this.multipartFile))
                .thenReturn(this.cloudinaryFileTest);
        Mockito.when(mockModelMapper.map(galleryEntity, ImageViewModel.class))
                .thenReturn(this.imageViewModelTest);


        Optional<ImageViewModel> actualView = this.galleryServiceToTest.uploadImage(uploadImageBinding);
        Assertions.assertEquals(actualView.get().getImageDescription(),
                this.imageViewModelTest.getImageDescription());
    }

}