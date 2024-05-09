package com.example.Reactive.Spring.Boot325.JWT.service.interfaces;

import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationService {

    Mono<AuthenticationResponse> authenticateRequest (AuthenticationRequest authenticationRequest);
}
