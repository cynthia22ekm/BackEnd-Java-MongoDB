package com.example.demo.service;

import com.example.demo.model.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class PostProductsResponse {

    private String message;
    private Products data;
    private HttpStatus status;
}
