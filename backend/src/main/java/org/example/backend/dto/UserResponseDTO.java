package org.example.backend.dto;

import org.example.backend.model.Role;

public record UserResponseDTO(Integer userId, String mail, String name, Role role) {
}
