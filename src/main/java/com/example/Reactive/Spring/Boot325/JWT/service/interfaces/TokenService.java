package com.example.Reactive.Spring.Boot325.JWT.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TokenService {
    String generateToken(UserDetails userDetails);
}
