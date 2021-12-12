package com.example.transferhall.models.bindingModels;

public class ContactFormBinding {
    private String senderName;
    private String email1;
    private String email2;
    private String subject;
    private String reference;
    private String message;

    public String getSenderName() {
        return senderName;
    }

    public ContactFormBinding setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public String getEmail1() {
        return email1;
    }

    public ContactFormBinding setEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactFormBinding setEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactFormBinding setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public ContactFormBinding setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ContactFormBinding setMessage(String message) {
        this.message = message;
        return this;
    }
}
