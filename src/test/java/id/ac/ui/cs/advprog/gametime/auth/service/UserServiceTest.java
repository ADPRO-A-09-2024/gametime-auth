package id.ac.ui.cs.advprog.gametime.auth.service;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user1@example.com");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user2@example.com");
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.allUsers();

        assertEquals(2, result.size());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("user2@example.com", result.get(1).getEmail());
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail("test@example.com");

        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserByEmail("test@example.com");
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testUpdateUserEmail() {
        User user = new User();
        user.setId(1);
        user.setEmail("old@example.com");

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setEmail("new@example.com");

        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUserEmail("new@example.com", user);

        assertEquals("new@example.com", result.getEmail());
    }

    @Test
    void testUpdateUserEmailAlreadyInUse() {
        User user = new User();
        user.setId(1);
        user.setEmail("old@example.com");

        when(userRepository.existsByEmail("new@example.com")).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUserEmail("new@example.com", user);
        });

        assertEquals("Email already in use", exception.getMessage());
    }

    @Test
    void testUpdateUserEmailUserNotFound() {
        User user = new User();
        user.setId(1);
        user.setEmail("old@example.com");

        when(userRepository.existsByEmail("new@example.com")).thenReturn(false);
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUserEmail("new@example.com", user);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1);

        when(userRepository.existsById(1)).thenReturn(true);

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUserNotFound() {
        User user = new User();
        user.setId(1);

        when(userRepository.existsById(1)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(user);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
