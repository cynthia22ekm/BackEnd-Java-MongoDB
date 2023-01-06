package com.example.demo.config;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class EmailServiceImpl {

   @Autowired
    private JavaMailSender emailSender;

    public void constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "?token=" + token;
        String message = "About the Company \n\n Maesn is an automation platform that allows you to connect and automate your most importend enterprise applications and services, saving you time and effort by automating repetitive tasks.\n\n Please click on the below link to reset the password";
        constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    public void constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        System.out.println("Subject of email is "+subject);
        System.out.println("Body of email is "+body);
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("testemailcyn124@gmail.com");
        emailSender.send(email);
    }


}
