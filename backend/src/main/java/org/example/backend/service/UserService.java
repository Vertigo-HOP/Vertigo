package org.example.backend.service;

import org.example.backend.dto.UserRequestDTO;
import org.example.backend.dto.UserResponseDTO;
import org.example.backend.model.User;
import org.example.backend.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO request){
        User user = new User();
        user.setMail(request.mail());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setRole(request.role());

        validateMail(user.getMail());
        validatePassword(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(user);

        return new UserResponseDTO(createdUser.getUserId(),
                createdUser.getMail(),
                createdUser.getName(),
                createdUser.getRole());
    }

    public void validateMail(String mail){
        if(userRepository.findByMail(mail).isPresent()){
            throw new IllegalArgumentException("Mail is already registered!");
        }
    }

    public void validatePassword(String password){
        if(password == null || password.length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        boolean containsUppercase = password.chars().anyMatch(c -> Character.isUpperCase(c));
        boolean containsDigit = password.chars().anyMatch(c -> Character.isDigit(c));

        if(!containsUppercase || !containsDigit){
            throw new IllegalArgumentException("Password must contain at least one uppercase and one number");
        }
    }

}
