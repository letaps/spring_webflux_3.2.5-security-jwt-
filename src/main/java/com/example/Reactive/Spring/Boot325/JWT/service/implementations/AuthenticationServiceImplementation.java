package com.example.Reactive.Spring.Boot325.JWT.service.implementations;

import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;
import com.example.Reactive.Spring.Boot325.JWT.service.interfaces.AuthenticationService;
import com.example.Reactive.Spring.Boot325.JWT.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenService tokenProvider;

    @Override
    public Mono<AuthenticationResponse> authenticateRequest(AuthenticationRequest authenticationRequest){
        return userDetailsService.findByUsername(authenticationRequest.username())
                .filter(u -> passwordEncoder.matches(authenticationRequest.Password(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(AuthenticationResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }
}
