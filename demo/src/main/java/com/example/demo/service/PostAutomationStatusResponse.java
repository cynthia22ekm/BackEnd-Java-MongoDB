package com.example.demo.service;

import com.example.demo.model.AutomationStatus;
import com.example.demo.model.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class PostAutomationStatusResponse {

    private AutomationStatus automationStatus;
    private HttpStatus status;
    private String message;
}
