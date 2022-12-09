package com.example.demo.repository;

import com.example.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRegistrationRepository extends MongoRepository<User, ObjectId> {

    User findByUserId(String userId);
}
