package id.ac.ui.cs.advprog.gametime.auth.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private String type;
}