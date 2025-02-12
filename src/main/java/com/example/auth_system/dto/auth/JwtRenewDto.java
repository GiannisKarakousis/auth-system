package com.example.auth_system.dto.auth;

public class JwtRenewDto {

    private String refreshAccessToken;

    public String getRefreshAccessToken() {
        return refreshAccessToken;
    }

    public void setRefreshAccessToken(String refreshAccessToken) {
        this.refreshAccessToken = refreshAccessToken;
    }

}