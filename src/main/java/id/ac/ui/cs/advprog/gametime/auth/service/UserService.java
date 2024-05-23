package id.ac.ui.cs.advprog.gametime.auth.service;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User updateUserEmail(String newEmail, User user) {
        if (userRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("Email already in use");
        }

        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setEmail(newEmail);
            return userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.delete(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}