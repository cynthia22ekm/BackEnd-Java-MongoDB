package com.example.demo.repository;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRegistrationRepository extends  MongoRepository<User, Long> {

    User findByUsername(String username);

    //User findByEmail(String email);
}
