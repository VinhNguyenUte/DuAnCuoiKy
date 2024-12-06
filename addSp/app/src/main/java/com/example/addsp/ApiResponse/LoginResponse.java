package com.example.addsp.ApiResponse;

public class LoginResponse {
    private String jwt;
    private String message;
    private boolean status;
    private Long userId;
    private String role;


    public LoginResponse(String jwt, String message, boolean status, Long userId,String role) {
        this.jwt = jwt;
        this.message = message;
        this.status = status;
        this.userId = userId;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
