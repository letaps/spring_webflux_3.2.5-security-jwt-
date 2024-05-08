package com.example.Reactive.Spring.Boot325.JWT.controllers;


import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.LoginResponse;
import com.example.Reactive.Spring.Boot325.JWT.service.JwtService;
import com.example.Reactive.Spring.Boot325.JWT.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/test")
@Slf4j
@AllArgsConstructor
public class TestReactiveController {

    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenService.TokenProvider tokenProvider;

    @GetMapping(path = "/unsecured")
    public Mono<String> helloWorldUnSecured(){
        return Mono.just("Hello World Un-Secured");

    }

    @GetMapping(path = "/secured")
    public Mono<String> helloWorldSecured(){
        return Mono.just("Hello World Secured");
    }

    @PostMapping(path = "/login")
    public Mono<LoginResponse> customLogin(@RequestBody final AuthenticationRequest authenticationRequest){
        return userDetailsService.findByUsername(authenticationRequest.username())
                .filter(u -> passwordEncoder.matches(authenticationRequest.Password(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }


}
