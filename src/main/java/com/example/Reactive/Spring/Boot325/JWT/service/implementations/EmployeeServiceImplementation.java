package com.example.Reactive.Spring.Boot325.JWT.service.implementations;


import com.example.Reactive.Spring.Boot325.JWT.dto.EmployeeRequest;
import com.example.Reactive.Spring.Boot325.JWT.entity.Employee;
import com.example.Reactive.Spring.Boot325.JWT.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImplementation {

    private final EmployeeRepository employeeRepository;

    public Mono<?> createUser(EmployeeRequest employeeRequest){
        Employee newEmployee = new Employee();
        newEmployee.setId(null);
        newEmployee.setUsername(employeeRequest.username());
        newEmployee.setPassword(employeeRequest.password());
        newEmployee.setRole(employeeRequest.role());
        newEmployee.setIsActive(true);

        return employeeRepository.findByUsername(employeeRequest.username())
                .flatMap(existingEmployee -> {
                    // If an employee with the same username exists, return an error
                    return Mono.error(new RuntimeException("Employee with username already exists"));
                })
                // If the employee with the provided username does not exist, save the new employee
                .switchIfEmpty(employeeRepository.save(newEmployee).onErrorResume(e -> Mono.error(new RuntimeException("Error occurred while saving employee"))));

    }
}
