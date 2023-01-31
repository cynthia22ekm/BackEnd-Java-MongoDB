package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String message;
    private Boolean status;
}
