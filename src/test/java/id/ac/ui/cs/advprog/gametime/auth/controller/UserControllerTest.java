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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(user);

        ResponseEntity<User> result = userController.getUserByEmail("test@example.com");

        assertEquals("test@example.com", result.getBody().getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userService.updateUserEmail("test@example.com", user)).thenReturn(user);

        ResponseEntity<User> result = userController.updateUser("test@example.com", user);

        assertEquals("test@example.com", result.getBody().getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(user);

        ResponseEntity<Void> result = userController.deleteUser("test@example.com");

        assertEquals(204, result.getStatusCodeValue());
    }
}