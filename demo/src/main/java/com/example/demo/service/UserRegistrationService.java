package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository repository;

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

    public PostUserResponse postUser(User user)
    {
        String userId = user.getUserId();
      User existingUser = repository.findByUserId(userId);
        System.out.println("User  is"+existingUser);
        if(existingUser == null) {
            repository.save(user);
            return new PostUserResponse(user,HttpStatus.OK,"User data saved successfully");
        }
        else
        {
            return new PostUserResponse(null, HttpStatus.CONFLICT, "User data already exists");
        }
    }

    public PostUserResponse updateUser(User user)
    {
        String userId = user.getUserId();
        User existingUser = repository.findByUserId(userId);
        if(existingUser == null) {
            return new PostUserResponse(null, HttpStatus.CONFLICT, "User data does not exist in DB for updation");
        }
        else
        {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            repository.save(existingUser);
            return new PostUserResponse(user, HttpStatus.OK, "User data updated successfully");
        }
    }
    public String deleteUser(String userId )
    {
        repository.deleteByUserId(userId);
        return "User deleted successfully";
    }
}
