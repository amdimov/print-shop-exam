package com.example.transferhall.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Unauthorized Request")
public class UnauthorizedRequest extends RuntimeException{
    public UnauthorizedRequest(String message) {
        super(message);
    }
}
