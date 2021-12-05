//package com.example.transferhall.web.controllers.admin;
//
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//@ControllerAdvice
//public class GlobalModel {
//
//    @ModelAttribute("logged")
//    private void checkLogoutAuthentication(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            model.addAttribute("logged", true);
//        }else {
//            model.addAttribute("logged", false);
//        }
//    }
//}
