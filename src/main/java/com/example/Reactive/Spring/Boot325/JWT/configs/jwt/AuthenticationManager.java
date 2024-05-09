package com.example.Reactive.Spring.Boot325.JWT.configs.jwt;

import com.example.Reactive.Spring.Boot325.JWT.service.implementations.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;
    private final ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        return Mono.just(authToken)
                .filter(jwtService::isTokenValid)
                .flatMap(token -> userDetailsService.findByUsername(jwtService.extractUsername(token))
                        .map(userDetails -> new JwtToken(token, userDetails))
                        .cast(Authentication.class))
                .map(auth -> ((JwtToken) auth).withAuthenticated(true))
                .switchIfEmpty(Mono.error(new JwtAuthenticationException("Invalid token.")));
    }
}