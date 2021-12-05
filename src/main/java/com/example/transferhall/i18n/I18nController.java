package com.example.transferhall.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class I18nController {
    private final LocaleResolver localeResolver;
    private final LocaleChangeInterceptor localeChangeInterceptor;
    private static final Logger LOGGER = LoggerFactory.getLogger(I18nController.class);

    public I18nController(LocaleResolver localeResolver, LocaleChangeInterceptor localeChangeInterceptor) {
        this.localeResolver = localeResolver;
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @GetMapping("{lang}/i18n")
    private String rendTempl(@PathVariable(required = false) String lang,
                             HttpServletRequest request,
                             HttpServletResponse response){
        LOGGER.info(LocaleContextHolder.getLocale().getLanguage());
        return "/tests/i18n/i18n_temp";
    }
    @GetMapping("/i18n")
    private String rendTempl(){
        LOGGER.info(LocaleContextHolder.getLocale().getLanguage());
        return "/tests/i18n/i18n_temp";
    }
}
