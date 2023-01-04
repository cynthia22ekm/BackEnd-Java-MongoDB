package com.example.demo.repository;

import com.example.demo.model.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String> {

}
