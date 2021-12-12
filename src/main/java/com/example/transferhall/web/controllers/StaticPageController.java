package com.example.transferhall.web.controllers;

import com.example.transferhall.models.bindingModels.ContactFormBinding;
import com.example.transferhall.web.email.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StaticPageController {
    private final EmailService emailService;
    private final String EMAIL_FROM_CONTACT_FORM = "office1@transferhall.de";
    private final String TEMPLATE_SUBJECT = "You have new message from contact form";
    public StaticPageController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/kontakt")
    public String getContactPage(){
        return "kontakt";
    }
    @PostMapping("/kontakt")
    public String sendEmailWithContactForm(ContactFormBinding contactFormBinding,
                                            RedirectAttributes redirectAttributes){
        String message = "From: " + contactFormBinding.getSenderName() + System.lineSeparator() +
                "Email: " + contactFormBinding.getEmail1() + System.lineSeparator() +
                "Reference: " + contactFormBinding.getReference() + System.lineSeparator() +
                "Subject: " + contactFormBinding.getSubject() + System.lineSeparator() +
                "Message: " + contactFormBinding.getMessage() + System.lineSeparator();
        emailService.
                setSimpleMessage(EMAIL_FROM_CONTACT_FORM, TEMPLATE_SUBJECT ,
                        message);
        redirectAttributes.addFlashAttribute("email", contactFormBinding.getEmail1());
        redirectAttributes.addFlashAttribute("message", contactFormBinding.getMessage());
        return "redirect:/kontakt/email-sent";
    }

    @GetMapping("/kontakt/email-sent")
    public String showEmailSendMessage(){
        return "email_sent";
    }

    @GetMapping("/services/single-spot")
    public String showSingleSpot(){
        return "static_pages/single_spot";
    }
    @GetMapping("/services/multi-spot")
    public String showMultiSpot(){
        return "static_pages/multi_spot";
    }
    @GetMapping("/services/photo-silk")
    public String showPhotoSilk(){
        return "static_pages/multi_spot";
    }
    @GetMapping("/preisrechner")
    public String showCalculator(){
        return "static_pages/preisrechner";
    }



}
