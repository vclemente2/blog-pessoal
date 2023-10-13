package com.generation.blogpessoal.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record loginDataDto(
        @Email(message = "The field email must be in a valid email format.")
        @NotBlank(message = "The email field is required.")
        String email,

        @NotBlank(message = "The password field is required.")
        String password
) {
}
