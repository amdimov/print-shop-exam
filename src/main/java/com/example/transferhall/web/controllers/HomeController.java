package com.example.transferhall.web.controllers;

import com.example.transferhall.models.SecurityUser;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homeRender(){
        return "index";
    }

}
