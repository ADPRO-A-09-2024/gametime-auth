package id.ac.ui.cs.advprog.gametime.auth.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginUserDtoTest {

    @Test
    void testLoginUserDto() {
        LoginUserDto dto = new LoginUserDto("test@example.com", "password123");

        assertEquals("test@example.com", dto.getEmail());
        assertEquals("password123", dto.getPassword());

        dto.setEmail("new@example.com");
        dto.setPassword("newPassword");

        assertEquals("new@example.com", dto.getEmail());
        assertEquals("newPassword", dto.getPassword());
    }
}
