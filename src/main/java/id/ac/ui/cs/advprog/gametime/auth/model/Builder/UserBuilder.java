package id.ac.ui.cs.advprog.gametime.auth.model.Builder;

<<<<<<< HEAD
import java.lang.IllegalArgumentException;
import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.model.Enum.UserType;


=======
import id.ac.ui.cs.advprog.gametime.auth.model.Enum.UserRole;
import id.ac.ui.cs.advprog.gametime.auth.model.User;

import java.lang.IllegalArgumentException;

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
public class UserBuilder {
    private String username;
    private String email;
    private String password;
<<<<<<< HEAD
    private String type;
    private Integer balance;

=======
    private String role;

    private int balance;

>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    public UserBuilder username(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
<<<<<<< HEAD
=======
        if (username.length() < 4) {
            throw new IllegalArgumentException("Username must be at least 4 characters long");
        }
        if (!username.matches("^[a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, and underscore");
        }
>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
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
<<<<<<< HEAD
    }

    public UserBuilder password(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.password = password;
        return this;
    }

    public UserBuilder type(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        if (!UserType.contains(type)) {
            throw new IllegalArgumentException("Invalid role");
        }
        this.type = type;
        return this;
    }

    public UserBuilder balance(Integer balance){
        this.balance = balance;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setType(type);
        user.setBalance(null);
        return user;
=======
>>>>>>> 665f0a0688c10125c02f09417b17a1a7592354b0
    }

    public UserBuilder password(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
        return this;
    }

    public UserBuilder role(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        if (!UserRole.contains(role)) {
            throw new IllegalArgumentException("Invalid role");
        }
        this.role = role;
        return this;
    }

    public UserBuilder balance(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        user.setBalance(balance);
        return user;
    }
}
