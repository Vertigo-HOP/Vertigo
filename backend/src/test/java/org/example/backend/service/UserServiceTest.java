package org.example.backend.service;

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

    @BeforeEach
    void setUp(){
        user = new User();
        user.setName("Test User");
        user.setMail("test@example.com");
        user.setPassword("Test1234");
    }

    @Test
    void createUserSuccess(){
        //arrange
        when(passwordEncoder.encode("Test1234")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        //act
        User result = userService.createUser(user);

        //assert
        assertNotNull(result);
        assertEquals(user.getMail(), result.getMail());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getRole(), result.getRole());
        assertEquals("encodedPassword", result.getPassword());

        verify(passwordEncoder, times(1)).encode("Test1234");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createUserFail(){
        //arrange
        when(passwordEncoder.encode("Test1234")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenThrow(new RuntimeException("Error"));

        //act & assert
        assertThrows(RuntimeException.class, ()-> {userService.createUser(user);});

        verify(passwordEncoder, times(1)).encode("Test1234");
        verify(userRepository, times(1)).save(user);
    }

}
