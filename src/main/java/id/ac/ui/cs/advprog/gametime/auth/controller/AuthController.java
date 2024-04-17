package id.ac.ui.cs.advprog.gametime.auth.controller;

import id.ac.ui.cs.advprog.gametime.auth.model.Builder.UserBuilder;
import id.ac.ui.cs.advprog.gametime.auth.model.UserEntity;
import id.ac.ui.cs.advprog.gametime.auth.model.dto.LoginDto;
import id.ac.ui.cs.advprog.gametime.auth.model.dto.RegisterDto;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // private final AuthenticationManager authenticationManager;
    // private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;

    // @Autowired
    // public AuthController(AuthenticationManager authenticationManager,
    //                       UserRepository userRepository,
    //                       PasswordEncoder passwordEncoder) {
    //     this.authenticationManager = authenticationManager;
    //     this.userRepository = userRepository;
    //     this.passwordEncoder = passwordEncoder;
    // }

    @PostMapping("/login")
    public String login(){
        return "Hello world";
    }

    @PostMapping("/register")
    public String register() {
        return "Hello world";
        }

    @PostMapping("/logout")
    public String logout() {
        return "Hello world";
    }
}
