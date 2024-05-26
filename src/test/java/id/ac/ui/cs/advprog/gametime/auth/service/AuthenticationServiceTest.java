package id.ac.ui.cs.advprog.gametime.auth.service;

import id.ac.ui.cs.advprog.gametime.auth.dtos.LoginUserDto;
import id.ac.ui.cs.advprog.gametime.auth.dtos.RegisterUserDto;
import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup() {
        RegisterUserDto registerUserDto = new RegisterUserDto(null, null, null, null);
        registerUserDto.setEmail("test@example.com");
        registerUserDto.setPassword("password");
        registerUserDto.setUsername("testuser");
        registerUserDto.setRole("BUYER");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setUsername("testuser");
        user.setRole("BUYER");
        user.setBalance(0);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authenticationService.signup(registerUserDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals("testuser", savedUser.getName());
        assertEquals("BUYER", savedUser.getRole());
        assertEquals(0, savedUser.getBalance());

        assertEquals(user, result);
    }

    @Test
    void testAuthenticate() {
        LoginUserDto loginUserDto = new LoginUserDto(null, null);
        loginUserDto.setEmail("test@example.com");
        loginUserDto.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setUsername("testuser");
        user.setRole("BUYER");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User result = authenticationService.authenticate(loginUserDto);

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("test@example.com", "password")
        );

        assertEquals(user, result);
    }

    @Test
    void testAuthenticateUserNotFound() {
        LoginUserDto loginUserDto = new LoginUserDto(null, null);
        loginUserDto.setEmail("test@example.com");
        loginUserDto.setPassword("password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            authenticationService.authenticate(loginUserDto);
        });

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("test@example.com", "password")
        );
    }
}
