package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UserRegistrationService {

    @Autowired
    private UserRegistrationRepository repository;

    @Autowired
    private SequenceGeneratorService generateSequence;


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

    public User getUserByUserName(String userName)
    {
        User user = repository.findByUserName(userName);
        return user;
    }

    public LoginResponse login(String userName, String password)
    {
        User user = getUserByUserName(userName);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        if(user == null) {
            return new LoginResponse(false,"User does not exists in the system");
         }
       else if(passwordEncoder.matches(password,user.getPassword()))
        {
            return new LoginResponse(true,"Logged In Successfullly");
        }
        else {
            return new LoginResponse(false,"Logged In Failed");
        }
    }
    public PostUserResponse postUser(User user)
    {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
           // String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(encodedPassword);
            user.setId(generateSequence.generateSequence(user.SEQUENCE_NAME));
            repository.save(user);
            return new PostUserResponse(user,HttpStatus.OK,"User data saved successfully");
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
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
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
