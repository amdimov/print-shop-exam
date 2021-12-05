package com.example.transferhall.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
public class PageNotFoundException extends RuntimeException{
    public PageNotFoundException() {
        super("Page Not Found");
    }
}
