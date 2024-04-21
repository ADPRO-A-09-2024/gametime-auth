package id.ac.ui.cs.advprog.gametime.auth.model.Builder;

import java.lang.IllegalArgumentException;
import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.model.Enum.UserType;


public class UserBuilder {
    private String username;
    private String email;
    private String password;
    private String role;

    public UserBuilder username(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.password = password;
        return this;
    }

    public UserBuilder role(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        if (!UserType.contains(role)) {
            throw new IllegalArgumentException("Invalid role");
        }
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        return user;
    }
}