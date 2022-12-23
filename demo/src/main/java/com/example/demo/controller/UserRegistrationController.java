package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.service.GetUserResponse;
import com.example.demo.service.LoginResponse;
import com.example.demo.service.PostUserResponse;
import com.example.demo.service.UserRegistrationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//https://www.javaguides.net/2021/10/login-and-registration-rest-api-using-spring-boot-spring-security-hibernate-mysql-database.html
//https://www.youtube.com/watch?v=sm-8qfMWEV8&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE
@RestController
@RequestMapping("/User")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService service;

     @RequestMapping(value="/GetUsers", method= RequestMethod.GET)
    public GetUserResponse getUsers()
    {
        return (GetUserResponse) service.getAllUsers();
    }

    @RequestMapping(value="/GetUserByUserName/{userName}", method= RequestMethod.GET)
    public User getUserWithUserName(@PathVariable String userName)
    {
        return service.getUserByUserName(userName);
    }

    @RequestMapping(value="/Login", method=RequestMethod.POST)
    public LoginResponse UserLogin(@RequestBody Login data )
    {
        System.out.println("Username inside login controller is"+data.getUsername());
        System.out.println("Password inside login controller is"+data.getPassword());
       String userName = data.getUsername();
       String password =data.getPassword();
       return service.login(userName, password);
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
