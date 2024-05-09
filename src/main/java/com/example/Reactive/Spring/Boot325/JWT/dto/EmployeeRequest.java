package com.example.Reactive.Spring.Boot325.JWT.dto;

import org.springframework.data.relational.core.mapping.Column;

public record EmployeeRequest( String username ,String password, String role) {
}
