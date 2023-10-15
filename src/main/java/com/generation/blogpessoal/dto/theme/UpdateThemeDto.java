package com.generation.blogpessoal.dto.theme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateThemeDto(
        @NotNull
        Long id,

        @NotBlank(message = "The name field is required.")
        @Size(min = 3, max = 255, message = "The name must be between 3 and 255 characters long.")
        String name
) {
}
