package id.ac.ui.cs.advprog.gametime.auth.model;

import id.ac.ui.cs.advprog.gametime.auth.model.Builder.UserBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(unique = true, length = 255, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private String role;

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}