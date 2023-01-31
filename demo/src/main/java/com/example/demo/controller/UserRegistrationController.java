package com.example.demo.controller;
import com.example.demo.config.JWTUtil;
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.model.ValidateTokenRequest;
import com.example.demo.model.ValidateTokenResponse;
import com.example.demo.service.GetUserResponse;
import com.example.demo.service.LoginResponse;
import com.example.demo.service.PostUserResponse;
import com.example.demo.service.UserRegistrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//References
//https://www.javaguides.net/2021/10/login-and-registration-rest-api-using-spring-boot-spring-security-hibernate-mysql-database.html
//https://www.youtube.com/watch?v=sm-8qfMWEV8&list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE
@RestController
@RequestMapping("/User")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService service;
    @Autowired
    private JWTUtil jwtUtil;

     @RequestMapping(value="/GetUsers", method= RequestMethod.GET)
    public GetUserResponse getUsers()
    {
        return (GetUserResponse) service.getAllUsers();
    }

    @RequestMapping(value="/GetUserByUserName/{username}", method= RequestMethod.GET)
    public User getUserWithUserName(@PathVariable String userName)
    {
        return service.getUserByUserName(userName);
    }

    /*@RequestMapping(value="/GetUserByEmail/{email}", method= RequestMethod.GET)
    public User getUserWithEmail(@PathVariable String email)
    {
        return service.getUserByEmail(email);
    }*/

    @RequestMapping(value="/auth/Login", method=RequestMethod.POST)
    public LoginResponse UserLogin(@RequestBody Login data )
    {
       String username = data.getUsername();
       String password =data.getPassword();
       /*Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username, password
                        )
                );

        UserDetails user = (UserDetails) authenticate.getPrincipal();*/

       return service.login(username, password);
    }


    @RequestMapping(value="/validateToken", method=RequestMethod.POST)
    public ValidateTokenResponse ValidateToken(@RequestBody ValidateTokenRequest data ) {
        try {
            String username = jwtUtil.getUsernameFromToken(data.getToken());
            return service.ValidateLoginToken(username, data.getToken());
        }
        catch(Exception e){
            return new ValidateTokenResponse(false, e.getMessage());
        }

    }


    @RequestMapping(value="/CreateUser", method=RequestMethod.POST)
    public PostUserResponse createUser(@RequestBody User user)
    {
          return service.postUser(user);
    }

    @RequestMapping(value="/UpdateUser/{userId}", method=RequestMethod.PUT)
    public PostUserResponse updateUser(@PathVariable Long userId ,@RequestBody User user) {
        return service.updateUser(userId,user);
    }

    @RequestMapping(value="/DeleteUser/{userId}", method = RequestMethod.DELETE)
    public String  deleteUser(@PathVariable Long userId)
    {
        return service.deleteUser(userId);
    }

}
