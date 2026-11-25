package org.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.backend.model.Role;

public record UserRequestDTO( @NotBlank @Email String mail, @NotBlank String name, @NotBlank String password, @NotNull Role role
                              ) {
}
