package com.example.demo.model;

public class ValidateTokenResponse {

    private Boolean status;
    private String message;

    public ValidateTokenResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
