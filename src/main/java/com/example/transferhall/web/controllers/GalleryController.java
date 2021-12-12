package com.example.transferhall.web.controllers;

import com.example.transferhall.models.views.ImageViewModel;
import com.example.transferhall.service.GalleryService;
import com.example.transferhall.util.exceptions.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/transfer-galerie")
public class GalleryController {
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/{pageNo}")
    public String showGallery(
            @PathVariable(required = false) Optional<Integer> pageNo,
            @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, Model model){

        int page = 1;
        if (pageNo.isPresent()){
            page = pageNo.get();
        }
        if (page < 1){
            page = 1;
        }
        Page<ImageViewModel> imagePage = galleryService.fetchPageableImages(page-1, pageSize, sortBy);
        model.addAttribute("image", imagePage);
        int totalPages = imagePage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.range(1, totalPages+1)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            if (page > pageNumbers.size()){
                throw new PageNotFoundException();
            }
        }

        return "gallery";
    }

    @GetMapping
    public String showGallery(){
        return "redirect:/transfer-galerie/1";
    }
}
