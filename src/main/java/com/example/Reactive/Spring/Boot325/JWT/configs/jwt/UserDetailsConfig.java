package com.example.Reactive.Spring.Boot325.JWT.configs.jwt;



import com.example.Reactive.Spring.Boot325.JWT.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class UserDetailsConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    ReactiveUserDetailsService userDetailsService(EmployeeRepository userRepository, PasswordEncoder passwordEncoder) {
        return username -> userRepository.findByUsername(username)
                .map(user -> User.withUsername(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .roles(user.getRole())
                        .disabled(!user.getIsActive())
                        .build());
    }
}
