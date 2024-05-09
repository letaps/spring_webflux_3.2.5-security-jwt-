package com.example.Reactive.Spring.Boot325.JWT.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Data
//@Builder
@Table("Employee")
public class Employee {
    @Id
    private UUID id;

    @Column("username")
    private String username ;
    @Column("password")
    private String password;

    @Column("role")
    private String role;

    @Column("isActive")
    private Boolean isActive;

}
