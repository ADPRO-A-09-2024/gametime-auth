package id.ac.ui.cs.advprog.gametime.auth.model.Enum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    @Test
    void testContains() {
        assertTrue(UserRole.contains("SELLER"));
        assertTrue(UserRole.contains("BUYER"));
        assertFalse(UserRole.contains("ADMIN"));
        assertFalse(UserRole.contains(""));
    }
}
