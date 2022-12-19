package com.example.demo.service;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetUserResponse {
    private List<User> data;
    private HttpStatus status;
    private String message;
}
