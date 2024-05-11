package com.example.Reactive.Spring.Boot325.JWT.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateToken(UserDetails userDetails);
}
