package com.example.demo.controller;

import com.example.demo.config.EmailServiceImpl;
import com.example.demo.model.ChangePassword;
import com.example.demo.model.CreateToken;
import com.example.demo.model.User;
import com.example.demo.service.TokenResponse;
import com.example.demo.service.PasswordResetTokenService;
import com.example.demo.service.UpdatePasswordResponse;
import com.example.demo.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Forgot-Password")
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenService service;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserRegistrationService registrationService;

    @RequestMapping(value = "/CreateToken",method= RequestMethod.POST)
    public TokenResponse getToken(@RequestBody CreateToken tokenData, HttpServletRequest request)
    {

        //User user = registrationService.getUserByEmail(tokenData.getEmail());
        User user = registrationService.getUserByUserName(tokenData.getEmail());
        if (user == null) {
            return new TokenResponse(null, "User does not exists in the database", false);
        } else
        {
            String token =  service.generateToken(tokenData.getEmail()).getToken();
            String path = "http://localhost:4200/reset-password";
            emailService.constructResetTokenEmail(path, request.getLocale(),token, user);
            return service.generateToken(tokenData.getEmail());
        }
    }

    @RequestMapping(value = "/ValidateToken/{token}",method= RequestMethod.GET)
    public TokenResponse redirectToChangePasswordPage(@PathVariable String token, Model model)
    {
        boolean isTokenValid = service.validatePasswordResetToken(token).getStatus();
        if(isTokenValid)
        {

            model.addAttribute("token", token);
            return new TokenResponse(token,"Navigate to Reset Password page", true);
        }
        else {
             return new TokenResponse(token,"Token is invalid or empty", false);
        }
    }

    @RequestMapping(value = "/ResetPassword",method= RequestMethod.POST)
    public UpdatePasswordResponse resetPassword(@RequestBody ChangePassword passwordData)
    {
    return service.updatePassword(passwordData);
    }


}
