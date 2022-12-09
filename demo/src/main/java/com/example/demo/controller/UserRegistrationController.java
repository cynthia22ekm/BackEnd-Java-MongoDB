package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.GetResponse;
import com.example.demo.service.PostUserResponse;
import com.example.demo.service.UserRegistrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService service;

    @RequestMapping(value="/GetUsers", method= RequestMethod.GET)
    public GetResponse getUsers()
    {
        return (GetResponse) service.getAllUsers();
    }

    @RequestMapping(value="/CreateUser", method=RequestMethod.POST)
    public PostUserResponse createUser(@RequestBody User user)
    {
        return service.postUser(user);
    }

    @RequestMapping(value="/UpdateUser", method=RequestMethod.PUT)
    public PostUserResponse updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value="/DeleteUser/{userId}", method = RequestMethod.DELETE)
    public String  deleteUser(@PathVariable String userId)
    {
        return service.deleteUser(userId);
    }

}
