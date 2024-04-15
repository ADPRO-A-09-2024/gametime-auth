package id.ac.ui.cs.advprog.gametime.auth.model.authentication;
import id.ac.ui.cs.advprog.gametime.auth.model.Balance;

import lombok.*;

import java.util.UUID;

import id.ac.ui.cs.advprog.gametime.auth.model.Balance;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userID;
    private String username;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saldo_id")
    private Balance balance;
    private String role;

    public static AuthUser createUser(String username, String email, String password, String role) {
        return AuthUser.builder()
                .username(username)
                .email(email)
                .password(password)
                .balance(new Balance())
                .role(role)
                .build();
    }


}

