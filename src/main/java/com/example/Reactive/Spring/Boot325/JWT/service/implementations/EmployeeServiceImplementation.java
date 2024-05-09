package com.example.Reactive.Spring.Boot325.JWT.service.implementations;


import com.example.Reactive.Spring.Boot325.JWT.dto.EmployeeRequest;
import com.example.Reactive.Spring.Boot325.JWT.entity.Employee;
import com.example.Reactive.Spring.Boot325.JWT.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImplementation {

    private final EmployeeRepository employeeRepository;

    public Mono<Employee> createUser(EmployeeRequest employeeRequest){
        Employee newEmployee = new Employee();
        newEmployee.setId(null);
        newEmployee.setUsername(employeeRequest.username());
        newEmployee.setPassword(employeeRequest.password());
        newEmployee.setRole(employeeRequest.role());
        newEmployee.setIsActive(true);

        return employeeRepository.save(newEmployee);
    }
}
