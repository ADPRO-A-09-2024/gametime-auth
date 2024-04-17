package id.ac.ui.cs.advprog.gametime.auth.model;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
public class UserEntity {
    private UUID userID;
    private String username;
    private String email;
    private String password;
    private Balance balance;
    private String type;
}

