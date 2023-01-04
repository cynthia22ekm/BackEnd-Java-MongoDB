package com.example.demo.repository;

import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String> {

}
