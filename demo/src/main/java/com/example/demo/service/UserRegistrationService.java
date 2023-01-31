package com.example.demo.service;
import com.example.demo.config.JWTUtil;
import com.example.demo.model.User;
import com.example.demo.model.ValidateTokenResponse;
import com.example.demo.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository repository;

    @Autowired
    private SequenceGeneratorService generateSequence;

    @Autowired
    private JWTUtil jwtUtil;


    public GetUserResponse getAllUsers()
    {
     List<User> allUsers =  repository.findAll();
      if(allUsers.size() ==0 )
      {
          return new GetUserResponse(null, HttpStatus.CONFLICT, "No data exists in DB");
      }
      else
          return new GetUserResponse(allUsers, HttpStatus.OK, "Data retrieved successfully");
    }

    public User getUserByUserName(String username)
    {
        User user = repository.findByUsername(username);
        return user;
    }

 /*   public User getUserByEmail(String email)
    {
        User user = repository.findByEmail(email);
        return user;
    }*/

    public LoginResponse login(String username, String password)
    {
        UserDetails userforToken = getUserByUserName(username);
        String token = jwtUtil.generateToken(userforToken);
        User user = getUserByUserName(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if(user == null) {
            return new LoginResponse(null,false,"User does not exists in the system" );
         }
       else if(passwordEncoder.matches(password,user.getPassword()))
        {
            return new LoginResponse(token,true,"Logged In Successfullly" );
        }
        else {
            return new LoginResponse(null,false,"Logged In Failed");
        }
    }

    public ValidateTokenResponse ValidateLoginToken(String username, String token)
    {
        try {
            User user = repository.findByUsername(username);
            if (jwtUtil.validateToken(token, user)) {
                return new ValidateTokenResponse(true, "Token is valid");
            } else {
                return new ValidateTokenResponse(false, "Token is invalid");
            }
        }
        catch(Exception e){
            return new ValidateTokenResponse(false, e.getMessage());
        }
    }
    public PostUserResponse postUser(User user)
    {
        String email = user.getUsername();
        User existingUser = repository.findByUsername(email);
        if(existingUser == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
           // String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(encodedPassword);
            user.setId(generateSequence.generateSequence(user.SEQUENCE_NAME));
            repository.save(user);
            return new PostUserResponse(user,HttpStatus.OK,"User data saved successfully");
        }
        else
        {
            return new PostUserResponse(null, HttpStatus.CONFLICT, "User already exists");
        }
    }

    public PostUserResponse updateUser(Long userId, User user)
    {
        User existingUser = repository.findById(userId).get();
        if(existingUser == null) {
            return new PostUserResponse(null, HttpStatus.CONFLICT, "User data does not exist in DB for updation");
        }
        else
        {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            //existingUser.setEmail(user.getEmail());
            existingUser.setUsername(user.getUsername());
            existingUser.setPhone(user.getPhone());
            existingUser.setCompany(user.getCompany());
            existingUser.setBillingaddress(user.getBillingaddress());
             repository.save(existingUser);
            return new PostUserResponse(user, HttpStatus.OK, "User data updated successfully");
        }
    }
    public String deleteUser(Long userId )
    {
        repository.deleteById(userId);
        return "User deleted successfully";
    }
}
