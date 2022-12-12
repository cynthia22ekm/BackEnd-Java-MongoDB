package com.example.demo.repository;

import com.example.demo.model.AutomationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutomationStatusRepository extends MongoRepository<AutomationStatus, String> {

    AutomationStatus findByAutomationName(String automationName);
}
