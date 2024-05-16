package com.furkanreyhan.SimpleLogin.responses;

public class JwtResponse {
    String accessToken;
    String message;
    String username;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken, String message, String username) {
        this.accessToken = accessToken;
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
