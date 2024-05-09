package com.example.Reactive.Spring.Boot325.JWT.configs.jwt;


import com.example.Reactive.Spring.Boot325.JWT.configs.jwt.SecurityContextRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class SecurityConfigs {
    public static final String[] PERMITTED_URL = new String[]{
            "/test/unsecured",
            "/test/login",
            "/test/createUser"

    };
    private final SecurityContextRepository securityContextRepository;
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

        return serverHttpSecurity
                //.authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .headers(headers -> headers.cache(ServerHttpSecurity.HeaderSpec.CacheSpec::disable))
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(PERMITTED_URL)
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .build();
    }
}
