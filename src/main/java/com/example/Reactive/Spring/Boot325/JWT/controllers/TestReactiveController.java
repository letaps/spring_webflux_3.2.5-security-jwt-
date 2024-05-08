package com.example.Reactive.Spring.Boot325.JWT.controllers;


import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;
import com.example.Reactive.Spring.Boot325.JWT.service.AuthenticationServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/test")
@Slf4j
@AllArgsConstructor
public class TestReactiveController {

    AuthenticationServiceImplementation authenticationServiceImplementation;
    @GetMapping(path = "/unsecured")
    public Mono<String> helloWorldUnSecured(){
        return Mono.just("Hello World Un-Secured");

    }

    @GetMapping(path = "/secured")
    public Mono<String> helloWorldSecured(){
        return Mono.just("Hello World Secured");
    }

    @PostMapping(path = "/login")
    public Mono<AuthenticationResponse> customLogin(@RequestBody final AuthenticationRequest authenticationRequest){
        return authenticationServiceImplementation.authenticateRequest(authenticationRequest);
    }


}
