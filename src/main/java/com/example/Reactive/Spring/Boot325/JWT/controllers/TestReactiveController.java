package com.example.Reactive.Spring.Boot325.JWT.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/test")
public class TestReactiveController {

    @GetMapping(path = "/unsecured")
    public Mono<String> helloWorldUnSecured(){
        return Mono.just("Hello World Un-Secured");

    }

    @GetMapping(path = "/secured")
    public Mono<String> helloWorldSecured(){
        return Mono.just("Hello World Secured");
    }

    @PostMapping(path = "/login")
    public Mono<String> customLogin(){
        return Mono.just("Login");
    }


}
