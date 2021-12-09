package com.example.transferhall.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invoice not found")
public class InvoiceNotFound extends RuntimeException{
    public InvoiceNotFound(String message) {
        super(message);
    }
}