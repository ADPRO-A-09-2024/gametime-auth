package id.ac.ui.cs.advprog.gametime.auth.model.Builder;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {

    @Test
    void testUsernameValidation() {
        UserBuilder builder = new UserBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.username(""));
        assertThrows(IllegalArgumentException.class, () -> builder.username("abc"));
        assertThrows(IllegalArgumentException.class, () -> builder.username("abc@"));
        assertDoesNotThrow(() -> builder.username("validUsername"));
    }

    @Test
    void testEmailValidation() {
        UserBuilder builder = new UserBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.email(""));
        assertThrows(IllegalArgumentException.class, () -> builder.email("invalidEmail"));
        assertDoesNotThrow(() -> builder.email("test@example.com"));
    }

    @Test
    void testPasswordValidation() {
        UserBuilder builder = new UserBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.password(""));
        assertThrows(IllegalArgumentException.class, () -> builder.password("short"));
        assertDoesNotThrow(() -> builder.password("validPassword"));
    }

    @Test
    void testRoleValidation() {
        UserBuilder builder = new UserBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.role(""));
        assertThrows(IllegalArgumentException.class, () -> builder.role("INVALID"));
        assertDoesNotThrow(() -> builder.role("SELLER"));
    }

    @Test
    void testBalanceValidation() {
        UserBuilder builder = new UserBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.balance(-1));
        assertDoesNotThrow(() -> builder.balance(0));
    }

    @Test
    void testBuild() {
        UserBuilder builder = new UserBuilder();
        User user = builder.username("validUsername")
                          .email("test@example.com")
                          .password("validPassword")
                          .role("BUYER")
                          .balance(100)
                          .build();

        assertEquals("validUsername", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("validPassword", user.getPassword());
        assertEquals("BUYER", user.getRole());
        assertEquals(100, user.getBalance());
    }
}
