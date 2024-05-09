package com.example.Reactive.Spring.Boot325.JWT;


import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationRequest;
import com.example.Reactive.Spring.Boot325.JWT.dto.AuthenticationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestController {
    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("Correct Credentials")
    void shouldLogin() {

        // given
        AuthenticationRequest loginRequest = new AuthenticationRequest("adamk", "password");

        // when
        AuthenticationResponse response = webTestClient.post()
                .uri("/login")
                .body(BodyInserters.fromValue(loginRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthenticationResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(response).isNotNull();
        assertThat(response.token()).isNotBlank();
    }

    @Test
    @DisplayName("Incorrect Credentials")
    void shouldNotLoginWithWrongPassword() {

        // given
        AuthenticationRequest loginRequest = new AuthenticationRequest("adamk", "passwedeord");

        // when / then
        webTestClient.post()
                .uri("/login")
                .body(BodyInserters.fromValue(loginRequest))
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
