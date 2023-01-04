package com.example.demo.service;

import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import com.example.demo.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository repository;

    @Autowired
    private UserRegistrationService service;

    public String generateToken(String email) {
        System.out.println("Email is "+email);
        User user = service.getUserByEmail(email);
        System.out.println("User is "+user);
        Date date = new Date();
        if (user == null) {
            return "User does not exists in the database";
        } else {
            String token = UUID.randomUUID().toString();
            PasswordResetToken myToken = new PasswordResetToken(UUID.randomUUID().toString(),token, user,date);
            repository.save(myToken);
            return token;
        }
    }
}
