package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.UserRequestDTO;
import org.example.backend.dto.UserResponseDTO;
import org.example.backend.model.User;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO request){
        User user = new User();
        user.setMail(request.mail());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setRole(request.role());

        User createdUser = userService.addUser(user);

        UserResponseDTO response = new UserResponseDTO(createdUser.getUserId(), createdUser.getMail(), createdUser.getName(), createdUser.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
