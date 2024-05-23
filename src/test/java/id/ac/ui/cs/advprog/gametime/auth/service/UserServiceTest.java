package id.ac.ui.cs.advprog.gametime.auth.service;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.allUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }


    @Test
    public void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserByEmail("test@example.com");

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testUpdateUserNotFound() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.save(user)).thenThrow(new RuntimeException("User not found"));

        assertThrows(RuntimeException.class, () -> userService.updateUser(user));
        verify(userRepository, times(1)).save(user);
    }


}