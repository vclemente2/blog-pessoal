package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.dto.user.CreateUserDto;
import com.generation.blogpessoal.dto.user.UpdateUserDto;
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
    @PostMapping
    public ResponseEntity<UserInfoDto> create(@Valid @RequestBody CreateUserDto data) {
        UserInfoDto createdUser = userService.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
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
    public ResponseEntity<UserInfoDto> update(@PathVariable Long id, @Valid @RequestBody UpdateUserDto data) {
        return ResponseEntity.ok(userService.update(id, data));
    }

}
