package com.furkanreyhan.SimpleLogin.responses;

public record JwtResponse(String accessToken, String message, String username ) {
    @Override
    public String toString() {
        return "JwtResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
