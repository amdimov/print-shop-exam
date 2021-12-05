package com.example.transferhall.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    void setSimpleMessage(String to, String subject, String text);

}
