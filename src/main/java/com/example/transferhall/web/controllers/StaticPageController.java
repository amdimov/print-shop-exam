package com.example.transferhall.web.controllers;

import com.example.transferhall.service.email.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {
    private final EmailService emailService;

    public StaticPageController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/kontakt")
    public String getKontaktPage(){
        emailService.
                setSimpleMessage("office1@transferhall.de", "Test Subject",
                        "Hello from Spring Boot in localhost");
        return "kontakt";
    }
}
