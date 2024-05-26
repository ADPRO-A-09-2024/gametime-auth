package id.ac.ui.cs.advprog.gametime.auth.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginResponseDtoTest {

    @Test
    void testLoginResponseDto() {
        LoginResponseDto dto = new LoginResponseDto();
        dto.setToken("sampleToken");
        dto.setExpiresIn(3600);

        assertEquals("sampleToken", dto.getToken());
        assertEquals(3600, dto.getExpiresIn());
    }
}
