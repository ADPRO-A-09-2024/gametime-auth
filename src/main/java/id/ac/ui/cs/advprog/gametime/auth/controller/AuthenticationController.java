package id.ac.ui.cs.advprog.gametime.auth.controller;

import id.ac.ui.cs.advprog.gametime.auth.dtos.LoginResponseDto;
import id.ac.ui.cs.advprog.gametime.auth.dtos.LoginUserDto;
import id.ac.ui.cs.advprog.gametime.auth.dtos.RegisterUserDto;
import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.service.AuthenticationService;
import id.ac.ui.cs.advprog.gametime.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Autowired
    private Executor asyncTaskExecutor;

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<User>>register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(registeredUser), asyncTaskExecutor);
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<LoginResponseDto>> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getRole());

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(loginResponse), asyncTaskExecutor);
    }
}