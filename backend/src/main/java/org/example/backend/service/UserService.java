package org.example.backend.service;

import org.example.backend.model.User;
import org.example.backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User createUser(User user){
        validateMail(user.getMail());
        validatePassword(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
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
