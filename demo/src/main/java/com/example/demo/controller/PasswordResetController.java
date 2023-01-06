package com.example.demo.controller;

import com.example.demo.config.EmailServiceImpl;
import com.example.demo.model.ChangePassword;
import com.example.demo.model.CreateToken;
import com.example.demo.model.User;
import com.example.demo.service.CreateTokenResponse;
import com.example.demo.service.PasswordResetTokenService;
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
    public CreateTokenResponse getToken(@RequestBody CreateToken tokenData, HttpServletRequest request)
    {

        User user = registrationService.getUserByEmail(tokenData.getEmail());
        if (user == null) {
            return new CreateTokenResponse(null, "User does not exists in the database");
        } else
        {
            String token =  service.generateToken(tokenData.getEmail()).getToken();
            String path = "http://localhost:4200/reset-password";
            emailService.constructResetTokenEmail(path, request.getLocale(),token, user);
            return service.generateToken(tokenData.getEmail());
        }
    }

    @RequestMapping(value = "/ValidateToken/{token}",method= RequestMethod.GET)
    public String redirectToChangePasswordPage(@PathVariable String token, Model model)
    {
        boolean isTokenValid = service.validatePasswordResetToken(token);
        if(isTokenValid)
        {

            model.addAttribute("token", token);
            System.out.println("Model is "+model);
            return "Navigate to Reset Password page";
        }
        else {
             return "Token is invalid or empty";
        }
    }

    @RequestMapping(value = "/ResetPassword",method= RequestMethod.POST)
    public String resetPassword(@RequestBody ChangePassword passwordData)
    {
    return service.updatePassword(passwordData);
    }


}
