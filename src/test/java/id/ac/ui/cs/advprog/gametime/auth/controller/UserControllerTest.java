package id.ac.ui.cs.advprog.gametime.auth.controller;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Executor asyncTaskExecutor;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }



    @Test
    public void testGetProfile() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        ResponseEntity<User> result = userController.getProfile("test@example.com");

        assertEquals("test@example.com", result.getBody().getEmail());
    }

    @Test
    public void testGetProfileNotFound() {
        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userController.getProfile("test@example.com"));
    }

    @Test
    public void testEditProfile() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userService.updateUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> result = userController.editProfile(user);

        assertEquals("test@example.com", result.getBody().getEmail());
    }
}