package com.example.Reactive.Spring.Boot325.JWT.dto;

import lombok.Data;

@Data
public class AuthenticationTokenData {
    private String userId;
    private String userAudience;
    private String deviceId;
    private String token;
}
