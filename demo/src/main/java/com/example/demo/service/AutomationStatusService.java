package com.example.demo.service;

import com.example.demo.model.AutomationStatus;
import com.example.demo.repository.AutomationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomationStatusService {

    @Autowired
    private AutomationStatusRepository repository;

    public GetAutomationResponse getAllAutomationStatus()
    {
        List<AutomationStatus> allStatus =  repository.findAll();
        if(allStatus == null)
        return new GetAutomationResponse(null, HttpStatus.CONFLICT, "No data returned");
        else
            return new GetAutomationResponse(allStatus, HttpStatus.OK, "Data returned successfully");
    }

    public AutomationStatus getAutomationStatusByName(String automationName)
    {
      return repository.findByAutomationName(automationName);
    }

    public PostAutomationStatusResponse postAutomationResponse(AutomationStatus automationStatus)
    {
        String statusId = automationStatus.getId();
        Optional<AutomationStatus> existingStatus = repository.findById(statusId);
        if(!existingStatus.isPresent()) {
            repository.save(automationStatus);
            return new PostAutomationStatusResponse(automationStatus,HttpStatus.OK,"Automation Status data saved successfully");
        }
        else
        {
            return new PostAutomationStatusResponse(null, HttpStatus.CONFLICT, "Automation Status data already exists");
        }
    }
}
