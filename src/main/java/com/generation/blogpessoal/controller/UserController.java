package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.dto.security.LoginDataDto;
import com.generation.blogpessoal.dto.security.TokenDataDto;
import com.generation.blogpessoal.dto.user.BodyDataUpdateUserDto;
import com.generation.blogpessoal.dto.user.CompleteUpdateUserDto;
import com.generation.blogpessoal.dto.user.CreateUserDto;
import com.generation.blogpessoal.dto.user.UserInfoDto;
import com.generation.blogpessoal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<UserInfoDto> create(@Valid @RequestBody CreateUserDto data) {
        UserInfoDto createdUser = userService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Transactional
    @PostMapping("/logar")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDataDto loginData) {
        TokenDataDto token = userService.authenticateUser(loginData);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDto>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDto> update(@RequestHeader("Authorization") String token, @PathVariable Long id, @Valid @RequestBody BodyDataUpdateUserDto data) {
        CompleteUpdateUserDto updateUserDto = new CompleteUpdateUserDto(data, id, token);
        return ResponseEntity.ok(userService.update(updateUserDto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        CompleteUpdateUserDto updateUserDto = new CompleteUpdateUserDto(id, token);
        userService.destroy(updateUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
