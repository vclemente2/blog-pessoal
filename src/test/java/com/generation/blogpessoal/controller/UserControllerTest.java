package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.domain.dto.user.CreateUserDto;
import com.generation.blogpessoal.domain.dto.user.UserInfoDto;
import com.generation.blogpessoal.domain.repository.UserRepository;
import com.generation.blogpessoal.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void start() {
        userRepository.deleteAll();
        userService.create(new CreateUserDto("Root", "root@root.com", "rootroot", null));
    }

    @Test
    @DisplayName("It should be able to create a new user with the correct payload.")
    void createUserWithCorrectPayload() {

        HttpEntity<CreateUserDto> requestBody = new HttpEntity<CreateUserDto>(new CreateUserDto("Teste", "teste@teste.com", "teste", null));

        ResponseEntity<UserInfoDto> respondeBody = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, requestBody, UserInfoDto.class);


        Assertions.assertEquals(HttpStatus.CREATED, respondeBody.getStatusCode());
        Assertions.assertEquals(requestBody.getBody().name(), respondeBody.getBody().name());
        Assertions.assertEquals(requestBody.getBody().email(), respondeBody.getBody().email());
        Assertions.assertNull(null);
    }
}
