package com.example.Reactive.Spring.Boot325.JWT.repositories;


import com.example.Reactive.Spring.Boot325.JWT.entity.Employee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends R2dbcRepository<Employee,Long> {
    Mono<Employee> findFirstByUsernameOrderByIdDesc(String username);
}
