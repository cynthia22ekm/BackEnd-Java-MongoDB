package com.example.demo.service;

import com.example.demo.model.ChangePassword;
import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import com.example.demo.repository.PasswordResetTokenRepository;
import com.example.demo.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository repository;

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    private UserRegistrationService service;


    public CreateTokenResponse generateToken(String email) {

        PasswordResetToken fetchedtoken = repository.findByEmail(email);
        Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
        if (fetchedtoken == null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken myToken = new PasswordResetToken(UUID.randomUUID().toString(), token, email, date);
            repository.save(myToken);
            return new CreateTokenResponse(token, "Token created successfully");
        } else {
            String token = UUID.randomUUID().toString();
            Date updatedDate = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
            fetchedtoken.setToken(token);
            fetchedtoken.setExpiryDate(updatedDate);
            repository.save(fetchedtoken);
            return new CreateTokenResponse(fetchedtoken.getToken(),  "Token updated successfully");
        }

    }

    public boolean validatePasswordResetToken(String token)
    {
        PasswordResetToken passwordToken =  repository.findByToken(token);
        if(passwordToken == null)
        {
            return false;
        }
        else {
            Calendar cal = Calendar.getInstance();
            return passwordToken.getExpiryDate().after(cal.getTime());
        }

    }

    public String updatePassword(ChangePassword passwordData){

        if(passwordData.getNewPassword().equals(passwordData.getConfirmPassword()))
        {
            String email = repository.findByToken(passwordData.getToken()).getEmail();
            User existingUser = userRepository.findByEmail(email);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(passwordData.getNewPassword());
            existingUser.setPassword(encodedPassword);
            userRepository.save(existingUser);
            return "Password Updated Successfully";
        }
        else
        {
            return "Passwords do not match";
        }

    }
}
