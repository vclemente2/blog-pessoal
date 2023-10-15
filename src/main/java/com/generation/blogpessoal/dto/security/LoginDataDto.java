package com.generation.blogpessoal.dto.security;

import com.generation.blogpessoal.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDataDto(
        @Email(message = "The field email must be in a valid email format.")
        @NotBlank(message = "The email field is required.")
        String email,

        @NotBlank(message = "The password field is required.")
        String password
) {
    public LoginDataDto(User user) {
        this(user.getEmail(), user.getPassword());
    }
}
