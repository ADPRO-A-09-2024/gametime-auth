package id.ac.ui.cs.advprog.gametime.auth.repository;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
}