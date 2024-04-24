package id.ac.ui.cs.advprog.gametime.auth.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;

public class UserTest {
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = User.builder()
                .email("user@email.com")
                .password("password")
                .role("buyer")
                .build();
    }

    @Test
    public void testUserBuilder() {
        User user = User.builder()
                .username("user")
                .email("user@email.com")
                .password("password")
                .role("buyer")
                .balance(0)
                .build();
        assertEquals("user", user.getUsername());
        assertEquals("user@email.com", user.getEmail());
        assertEquals("buyer", user.getRole());
        assertEquals(0, user.getBalance());
    }

    @Test
    public void testUserBuilderWithInvalidRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().role("MEOW");
        });
    }

    @Test
    public void testUserBuilderWithValidRole() {
        User user = User.builder()
                .role("buyer")
                .build();
        assertEquals("buyer", user.getRole());
        user = User.builder()
                .role("seller")
                .build();
        assertEquals("seller", user.getRole());
    }

    @Test
    public void testUserBuilderWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().username("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().username(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().username(" ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().username(" asd");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().username("as$df");
        });
    }

    @Test
    public void testUserBuilderWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email(" @email.com");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email("asdf");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email("asdf@");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email("asdf@.com");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().email("asdf@com");
        });
    }

    @Test
    public void testUserBuilderWithInvalidPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().password("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().password(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().password(" ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().password(" asdf");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User.builder().password("as$df");
        });
    }

    @Test
    public void testUserDetailImplGetUsername() {
        assertEquals("user@email.com", mockUser.getUsername());
    }

    @Test
    public void testUserDetailImplGetAuthorities() {
        assertEquals(List.of(), mockUser.getAuthorities());
    }

    @Test
    public void testUserDetailImplIsAccountNonExpired() {
        assertTrue(mockUser.isAccountNonExpired());
    }

    @Test
    public void testUserDetailImplIsAccountNonLocked() {
        assertTrue(mockUser.isAccountNonLocked());
    }

    @Test
    public void testUserDetailImplIsEnabled() {
        assertTrue(mockUser.isEnabled());
    }

    @Test
    public void testUserDetailImplIsCredentialsNonExpired() {
        assertTrue(mockUser.isCredentialsNonExpired());
    }
}
