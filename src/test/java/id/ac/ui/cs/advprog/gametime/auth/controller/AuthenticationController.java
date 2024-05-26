package id.ac.ui.cs.advprog.gametime.auth.controller;

import id.ac.ui.cs.advprog.gametime.auth.dtos.LoginResponseDto;
import id.ac.ui.cs.advprog.gametime.auth.dtos.LoginUserDto;
import id.ac.ui.cs.advprog.gametime.auth.dtos.RegisterUserDto;
import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.service.AuthenticationService;
import id.ac.ui.cs.advprog.gametime.auth.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegister() {
        // Prepare test data
        RegisterUserDto registerUserDto = new RegisterUserDto("test@example.com", "password", "username", "BUYER");
        User registeredUser = new User();
        registeredUser.setEmail(registerUserDto.getEmail());

        // Mock the service method
        when(authenticationService.signup(registerUserDto)).thenReturn(registeredUser);

        // Call the controller method
        CompletableFuture<ResponseEntity<User>> response = authenticationController.register(registerUserDto);

        // Assert the response
        ResponseEntity<User> entity;
        try {
            entity = response.get();
            assertEquals(HttpStatus.OK, entity.getStatusCode());
            assertEquals(registeredUser, entity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAuthenticate() {
        // Prepare test data
        LoginUserDto loginUserDto = new LoginUserDto("test@example.com", "password");
        User authenticatedUser = new User();
        authenticatedUser.setEmail(loginUserDto.getEmail());

        // Mock the service methods
        when(authenticationService.authenticate(loginUserDto)).thenReturn(authenticatedUser);
        when(jwtService.generateToken(authenticatedUser, authenticatedUser.getRole())).thenReturn("sampleToken");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        // Call the controller method
        CompletableFuture<ResponseEntity<LoginResponseDto>> response = authenticationController.authenticate(loginUserDto);

        // Assert the response
        ResponseEntity<LoginResponseDto> entity;
        try {
            entity = response.get();
            assertEquals(HttpStatus.OK, entity.getStatusCode());
            assertEquals("sampleToken", entity.getBody().getToken());
            assertEquals(3600L, entity.getBody().getExpiresIn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
