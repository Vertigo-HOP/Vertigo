package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.UserRequestDTO;
import org.example.backend.dto.UserResponseDTO;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   // @PreAuthorize("hasRole('ADMIN')") - Fjernet indtil login funktionalitet er implementeret.
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request){
        UserResponseDTO response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // @PreAuthorize("hasRole('ADMIN')") - Fjernet indtil login funktionalitet er implementeret.
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> readAllUsers (){
        return ResponseEntity.ok(userService.readAllUsers());
    }

}

