package com.generation.blogpessoal.dto.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @NotNull(message = "The id field is required.")
        Long id,

        @Size(min = 3, max = 255, message = "The name must be between 3 and 255 characters long.")
        String name,

        @Max(value = 255, message = "The email field must be shorter than 255 characters.")
        String email,

        @Size(min = 5, max = 30, message = "The password must be between 3 and 255 characters long.")
        String password,

        @Max(value = 5000, message = "The image field must be shorter than 5000 characters.")
        String image
) {
}
