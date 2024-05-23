package id.ac.ui.cs.advprog.gametime.auth.service;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        assertEquals(2, userService.allUsers().size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("test@test.com");
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

        assertEquals(user, userService.getUserByEmail("test@test.com"));
        verify(userRepository, times(1)).findByEmail("test@test.com");
    }

    @Test
    public void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserByEmail("test@test.com"));
        verify(userRepository, times(1)).findByEmail("test@test.com");
    }

    @Test
    public void testUpdateUserEmail() {
        User user = new User();
        user.setId(1);
        user.setEmail("old@test.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail("new@test.com")).thenReturn(false);

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setEmail("new@test.com");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User userToSave = invocation.getArgument(0);
            user.setEmail(userToSave.getEmail());
            return user;
        });

        User result = userService.updateUserEmail("new@test.com", user);

        assertEquals("new@test.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserEmailUserNotFound() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUserEmail("new@test.com", user));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void testUpdateUserEmailEmailInUse() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail("new@test.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.updateUserEmail("new@test.com", user));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1);
        when(userRepository.existsById(1)).thenReturn(true);

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserNotFound() {
        User user = new User();
        user.setId(1);
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteUser(user));
        verify(userRepository, times(0)).delete(user);
    }


}