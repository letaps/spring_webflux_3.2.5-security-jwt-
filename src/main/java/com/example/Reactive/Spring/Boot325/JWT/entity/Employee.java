package com.example.Reactive.Spring.Boot325.JWT.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Data
public class Employee {
    @Id
    private UUID id;
    private String username ;
    private String password;
    private String role;
    private Boolean isActive;

}
