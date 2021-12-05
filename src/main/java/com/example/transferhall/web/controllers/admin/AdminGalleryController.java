package com.example.transferhall.web.controllers.admin;

import com.example.transferhall.models.bindingModels.admin.binding.UploadImageBinding;
import com.example.transferhall.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/content")
public class AdminGalleryController {
    private GalleryService galleryService;

    public AdminGalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping("/upload-image")
    public String uploadImage(@Valid UploadImageBinding uploadBinding, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("binding", uploadBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.binding", bindingResult);
            return "redirect:upload-image";
        }
        try {
            galleryService.uploadImage(uploadBinding);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("uploaded", true);
        return "redirect:upload-image";
    }
    @GetMapping("/upload-image")
    public String uploadImage(){
        return "admin/upload_image";
    }

    @ModelAttribute("binding")
    public UploadImageBinding uploadImageBinding(){
        return new UploadImageBinding();
    }

    @Transactional
    @DeleteMapping("/delete-image")
    public String deleteImage(@RequestParam("publicId") String publicId){
        try {
            this.galleryService.deleteImage(publicId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/transfer-galerie";
    }


}
