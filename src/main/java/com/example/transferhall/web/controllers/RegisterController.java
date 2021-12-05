package com.example.transferhall.web.controllers;

import com.example.transferhall.models.bindingModels.UserRegisterBindingModel;
import com.example.transferhall.models.serviceModels.UserRegisterServiceModel;
import com.example.transferhall.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UsersService usersService;
    private ModelMapper modelMapper;

    public RegisterController(UsersService usersService,
                               ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String registerRender(){
        return "register";
    }

    @PostMapping
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult
            , RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBind", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBind",
                    bindingResult);
            return "redirect:/register";
        }

        this.usersService
                .register(modelMapper.map(userRegisterBindingModel,
                        UserRegisterServiceModel.class));

        return "redirect:/login";
    }

    @ModelAttribute(name = "userRegisterBind")
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
}

















