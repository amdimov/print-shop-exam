package com.example.transferhall.tests;

import com.example.transferhall.models.views.ImageViewModel;
import com.example.transferhall.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
    private final GalleryService galleryService;

    public TestController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

//    @GetMapping("/test-cache-gallery")
//    public String testCachable(Model model){
//        List<ImageViewModel> images = galleryService.fetchAllImages();
//        model.addAttribute("image", images);
//        return "gallery";
//    }
}
