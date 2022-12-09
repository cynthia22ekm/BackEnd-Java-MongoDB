package com.example.demo.service;

import com.example.demo.model.Products;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class PostUserResponse {
    private User user;
    private HttpStatus status;
    private String message;

}
