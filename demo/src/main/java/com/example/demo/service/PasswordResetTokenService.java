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


    public TokenResponse generateToken(String email) {

        PasswordResetToken fetchedtoken = repository.findByEmail(email);
        Date date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
        if (fetchedtoken == null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken myToken = new PasswordResetToken(UUID.randomUUID().toString(), token, email, date);
            repository.save(myToken);
            return new TokenResponse(token, "Token created successfully", true);
        } else {
            String token = UUID.randomUUID().toString();
            Date updatedDate = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
            fetchedtoken.setToken(token);
            fetchedtoken.setExpiryDate(updatedDate);
            repository.save(fetchedtoken);
            return new TokenResponse(fetchedtoken.getToken(),  "Token updated successfully", true);
        }

    }

    public TokenResponse validatePasswordResetToken(String token)
    {
        PasswordResetToken passwordToken =  repository.findByToken(token);
        if(passwordToken == null)
        {
            return new TokenResponse(token,"PasswordToken is empty",false);
        }
        else {
            Calendar cal = Calendar.getInstance();
            return new TokenResponse(token,"PasswordToken status",passwordToken.getExpiryDate().after(cal.getTime()));
        }

    }

    public UpdatePasswordResponse updatePassword(ChangePassword passwordData){

        if(passwordData.getNewPassword().equals(passwordData.getConfirmPassword()))
        {
            String email = repository.findByToken(passwordData.getToken()).getEmail();
            //User existingUser = userRepository.findByEmail(email);
            User existingUser = userRepository.findByUsername(email);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(passwordData.getNewPassword());
            existingUser.setPassword(encodedPassword);
            userRepository.save(existingUser);
            return new UpdatePasswordResponse("Password Updated Successfully", true);
        }
        else
        {
            return new UpdatePasswordResponse("Passwords do not match", false);
        }

    }
}
