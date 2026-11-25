package org.example.backend.service;

import org.example.backend.dto.UserRequestDTO;
import org.example.backend.dto.UserResponseDTO;
import org.example.backend.model.Role;
import org.example.backend.model.User;
import org.example.backend.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDTO request;

    @BeforeEach
    void setUp(){
        request = new UserRequestDTO("test@example.com", "Test user", "Test1234", Role.ADMIN);

        user = new User();
        user.setUserId(1);
        user.setName("Test User");
        user.setMail("test@example.com");
        user.setPassword("Test1234");
        user.setRole(Role.ADMIN);

    }

    @Test
    void createUserSuccess(){
        //arrange
        when(passwordEncoder.encode("Test1234")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        //act
        UserResponseDTO result = userService.createUser(request);

        //assert
        assertNotNull(result);
        assertEquals(user.getUserId(), result.userId());
        assertEquals(user.getMail(), result.mail());
        assertEquals(user.getName(), result.name());
        assertEquals(user.getRole(), result.role());

        verify(passwordEncoder, times(1)).encode("Test1234");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUserFail(){
        //arrange
        when(passwordEncoder.encode("Test1234")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Error"));

        //act & assert
        assertThrows(RuntimeException.class, ()-> {userService.createUser(request);});

        verify(passwordEncoder, times(1)).encode("Test1234");
        verify(userRepository, times(1)).save(any(User.class));
    }

}
