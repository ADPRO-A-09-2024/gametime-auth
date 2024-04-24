package id.ac.ui.cs.advprog.gametime.auth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;

    private long expiresIn;
}
