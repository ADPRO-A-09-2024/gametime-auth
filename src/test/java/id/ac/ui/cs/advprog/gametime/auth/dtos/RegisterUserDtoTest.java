package id.ac.ui.cs.advprog.gametime.auth.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterUserDtoTest {

    @Test
    void testRegisterUserDto() {
        RegisterUserDto dto = new RegisterUserDto("test@example.com", "password123", "testUser", "BUYER");

        assertEquals("test@example.com", dto.getEmail());
        assertEquals("password123", dto.getPassword());
        assertEquals("testUser", dto.getUsername());
        assertEquals("BUYER", dto.getRole());

        dto.setEmail("new@example.com");
        dto.setPassword("newPassword");
        dto.setUsername("newUser");
        dto.setRole("SELLER");

        assertEquals("new@example.com", dto.getEmail());
        assertEquals("newPassword", dto.getPassword());
        assertEquals("newUser", dto.getUsername());
        assertEquals("SELLER", dto.getRole());
    }
}
