package id.ac.ui.cs.advprog.gametime.auth.repository;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("BUYER");
        user.setBalance(100);
        userRepository.save(user);
    }

    @Test
    void testFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void testExistsByEmail() {
        boolean exists = userRepository.existsByEmail("test@example.com");
        assertTrue(exists);
    }

    @Test
    void testSaveUser() {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setEmail("new@example.com");
        newUser.setPassword("newpassword");
        newUser.setRole("SELLER");
        newUser.setBalance(200);

        User savedUser = userRepository.save(newUser);
        assertNotNull(savedUser.getId());
        assertEquals("newuser", savedUser.getName());
        assertEquals("new@example.com", savedUser.getEmail());
        assertEquals("newpassword", savedUser.getPassword());
        assertEquals("SELLER", savedUser.getRole());
        assertEquals(200, savedUser.getBalance());
    }

    @Test
    void testDeleteUser() {
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}
