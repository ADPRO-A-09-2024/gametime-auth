package id.ac.ui.cs.advprog.gametime.auth.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("BUYER");
        user.setBalance(100);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1, user.getId());
        assertEquals("testuser", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("BUYER", user.getRole());
        assertEquals(100, user.getBalance());
    }

    @Test
    void testUserDetailsMethods() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
        assertTrue(user.getAuthorities().isEmpty());
    }

    @Test
    void testBuilder() {
        User builtUser = User.builder()
                             .username("builtuser")
                             .email("built@example.com")
                             .password("password123")
                             .role("SELLER")
                             .balance(200)
                             .build();

        assertEquals("builtuser", builtUser.getName());
        assertEquals("built@example.com", builtUser.getEmail());
        assertEquals("password123", builtUser.getPassword());
        assertEquals("SELLER", builtUser.getRole());
        assertEquals(200, builtUser.getBalance());
    }
}
