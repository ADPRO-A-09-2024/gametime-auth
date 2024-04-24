package id.ac.ui.cs.advprog.gametime.auth.repository;

import id.ac.ui.cs.advprog.gametime.auth.model.User;
import id.ac.ui.cs.advprog.gametime.auth.model.Enum.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User mockUser;

    private User setupUser(String email, UserRole role) {
        return User.builder()
                .username("user12345")
                .email(email)
                .password("password")
                .role(role.toString())
                .build();
    }

    @BeforeEach
    void setUp() {
        userRepository.save(setupUser("seller1@email.com", UserRole.SELLER));
        userRepository.save(setupUser("buyer1@email.com", UserRole.BUYER));
    }

    @Test
    void findSellerByEmail() {
        Optional<User> user = userRepository.findByEmail("seller1@email.com");
        if (user.isPresent()) {
            assertEquals(mockUser.getEmail(), user.get().getEmail());
        } else {
            fail("Seller not found");
        }
    }

    @Test
    void findBuyerByEmail() {
        Optional<User> user = userRepository.findByEmail("buyer1@email.com");
        if (user.isPresent()) {
            assertEquals(mockUser.getEmail(), user.get().getEmail());
        } else {
            fail("Buyer not found");
        }
    }

    @Test
    void findSellerByEmailNotFound() {
        Optional<User> user = userRepository.findByEmail("noseller@email.com");
        assertFalse(user.isPresent());
    }

    @Test
    void findBuyerByEmailNotFound() {
        Optional<User> user = userRepository.findByEmail("nobuyer@email.com");
        assertFalse(user.isPresent());
    }

    @Test
    void createSeller() {
        String testEmail = "testseller@test.com";
        User user = setupUser(testEmail, UserRole.SELLER);
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(testEmail);
        if (savedUser.isPresent()) {
            assertEquals(user.getEmail(), savedUser.get().getEmail());
        } else {
            fail("Seller not found");
        }
    }

    @Test
    void createBuyer() {
        String testEmail = "testbuyer@test.com";
        User user = setupUser(testEmail, UserRole.BUYER);
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(testEmail);
        if (savedUser.isPresent()) {
            assertEquals(user.getEmail(), savedUser.get().getEmail());
        } else {
            fail("Buyer not found");
        }
    }

    @Test
    void deleteSeller() {
        String testEmail = "testseller@test.com";
        User user = setupUser(testEmail, UserRole.SELLER);
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(testEmail);
        if (savedUser.isPresent()) {
            userRepository.delete(savedUser.get());
            Optional<User> deletedUser = userRepository.findByEmail(testEmail);
            assertFalse(deletedUser.isPresent());
        } else {
            fail("Seller not found");
        }
    }

    @Test
    void deleteBuyer() {
        String testEmail = "testbuyer@test.com";
        User user = setupUser(testEmail, UserRole.BUYER);
        userRepository.save(user);
        Optional<User> savedUser = userRepository.findByEmail(testEmail);
        if (savedUser.isPresent()) {
            userRepository.delete(savedUser.get());
            Optional<User> deletedUser = userRepository.findByEmail(testEmail);
            assertFalse(deletedUser.isPresent());
        } else {
            fail("Buyer not found");
        }
    }
}