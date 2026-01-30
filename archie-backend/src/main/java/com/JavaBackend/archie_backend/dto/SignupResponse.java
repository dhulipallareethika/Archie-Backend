package com.JavaBackend.archie_backend.dto;

public class SignupResponse {

    private String userId;
    private String email;
    private String username;
    private String message;

    public SignupResponse(String userId, String email, String username, String message) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.message = message;
    }
    public String getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getMessage() {
        return message;
    }
}
