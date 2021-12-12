package com.example.transferhall.service.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class I18nController {
    private static final Logger LOGGER = LoggerFactory.getLogger(I18nController.class);

    @GetMapping("/i18n")
    private String rendTempl(){
        return "/tests/i18n/i18n_temp";
    }
}
