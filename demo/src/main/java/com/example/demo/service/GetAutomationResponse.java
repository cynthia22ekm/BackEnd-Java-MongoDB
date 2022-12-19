package com.example.demo.service;

import com.example.demo.model.AutomationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetAutomationResponse {

    private List<AutomationStatus> data;
    private HttpStatus status;
    private String message;

}
