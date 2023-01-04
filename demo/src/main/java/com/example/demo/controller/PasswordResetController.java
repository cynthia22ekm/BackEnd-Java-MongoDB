package com.example.demo.controller;

import com.example.demo.model.PasswordResetToken;
import com.example.demo.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class PasswordResetController {

    @Autowired
    private PasswordResetTokenService service;

    @RequestMapping(value = "/CreateToken/{email}",method= RequestMethod.POST)
    public String getToken(@PathVariable String email)
    {
       return service.generateToken(email);
    }

}
