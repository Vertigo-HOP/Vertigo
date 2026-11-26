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

import java.util.List;

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

    @Test
    void readAllUsersSuccess(){
        //arrange
        User user2 = new User();
        user2.setUserId(2);
        user2.setMail("emp@example.com");
        user2.setName("Employee");
        user2.setRole(Role.EMPLOYEE);

        when(userRepository.findAll()).thenReturn(List.of(user, user2));

        //act
        List<UserResponseDTO> result = userService.readAllUsers();

        //assert
        assertEquals(2, result.size());

        assertEquals(1, result.get(0).userId());
        assertEquals("test@example.com", result.get(0).mail());
        assertEquals("Test User", result.get(0).name());
        assertEquals(Role.ADMIN, result.get(0).role());

        assertEquals(2, result.get(1).userId());
        assertEquals("emp@example.com", result.get(1).mail());
        assertEquals("Employee", result.get(1).name());
        assertEquals(Role.EMPLOYEE, result.get(1).role());

        verify(userRepository).findAll();
    }

    @Test
    void readAllUsersFail(){
        //arrange
        when(userRepository.findAll()).thenThrow(new RuntimeException("Error"));

        //act & assert
        assertThrows(RuntimeException.class, ()->{userService.readAllUsers();});

        verify(userRepository).findAll();
    }

}
