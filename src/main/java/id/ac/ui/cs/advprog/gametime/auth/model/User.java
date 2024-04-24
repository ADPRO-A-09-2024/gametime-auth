package id.ac.ui.cs.advprog.gametime.auth.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.gametime.auth.model.Builder.UserBuilder;
>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

<<<<<<< HEAD
=======

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Getter
    private Integer id;
<<<<<<< HEAD
    @Column(nullable = false)
    @Setter
    private String username;
=======

    @Column(nullable = false)
    @Setter
    private String username;

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    @Column(unique = true, length = 255, nullable = false)
    @Getter
    @Setter
    private String email;
<<<<<<< HEAD
    @Column(nullable = false)
    @Getter
    @Setter
    private String password;
=======

    @Column(nullable = false)
    @Getter
    @Setter
    @JsonIgnore
    private String password;

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    @Column(nullable = false)
    @Getter
    @Setter
    private String role;

    @Column(nullable = false)
    @Getter
    @Setter
<<<<<<< HEAD
    private Integer balance;
=======
    private int balance;
>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0

    public static UserBuilder builder() {
        return new UserBuilder();
    }
<<<<<<< HEAD
=======

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    public String getName() {
        return username;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
<<<<<<< HEAD
=======

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
<<<<<<< HEAD
=======

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    @Override
    public boolean isEnabled() {
        return true;
    }
<<<<<<< HEAD
=======

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
<<<<<<< HEAD
}
=======
}


>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
