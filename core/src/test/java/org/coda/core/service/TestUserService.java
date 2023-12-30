package org.coda.core.service;

import org.coda.core.entities.User;
import org.coda.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 /**
 * Test class for UserService.
 * This class tests various functionalities of the UserService including
 * user creation, login, and retrieving users based on specific criteria.
 */
@ExtendWith(MockitoExtension.class)

class TestUserService {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    UserService userService;

    private User user;

    /**
     * Set up a user object before each test
     * This user object will be used in the tests
     */

    @BeforeEach
    void setUp() {
        user = new User();
        User.Contact contact = new User.Contact();
        contact.setEmail("cc@test.com");
        user.setContact(contact);
        user.setPassword("$2a$10"); // BCrypt encoded password

    }

    /**
     * Test the createUser method of the UserService
     * This test checks if the user is created successfully
     * and if the user is saved in the database
     */

    @Test
    void testCreateUser_Success() {

        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        Optional<User> result = userService.createUser(user);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).save(any(User.class));
    }

    /**
     * Test the createUser method of the UserService for failure
     * This test checks if the user is not created successfully
     * and if the user is not saved in the database
     * This test is expected to fail
     * The test will pass if the createUser method throws an exception
     */

    @Test
    void testCreateUser_Fail() {

        // Arrange
        doThrow(RuntimeException.class).when(userRepository).save(any(User.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.createUser(user));
        verify(userRepository, times(1)).save(any(User.class));
    }

    /**
     * Test the loginUser method of the UserService
     * This test checks if the user is logged in successfully
     * and if the user is saved in the database
     */

    @Test
    void testLoginUser_Success() {

        //== Arrange ==
        String email = "user@example.com";
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword);
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setAge(23);
        user.setPassword(encodedPassword); // BCrypt encoded password
        User.Contact contact = new User.Contact();
        contact.setEmail(email);
        user.setContact(contact);

        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(encoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        //== Act ==
        Optional<User> result = userService.loginUser(email, rawPassword);

        //== Assert ==
        assertTrue(result.isPresent());
        assertEquals(user, result.get()); // Check if the returned user is the same as the input user
        verify(userRepository).findUserByEmail(email);
        verify(encoder).matches(rawPassword, encodedPassword); // Verify that the password check is performed

    }

    /**
     * Test the loginUser method of the UserService for failure
     * This test checks if the user is not logged in successfully
     * and if the user is not saved in the database
     * This test is expected to fail
     * The test will pass if the loginUser method throws an exception
     */



    @Test
    void testLoginUser_UserNotFound() {

        // Arrange
        String email = "nonexistent@example.com";
        String password = "password";

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.loginUser(email, password);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository).findUserByEmail(email);
        verify(encoder, never()).matches(anyString(), anyString());
    }

    /**
     * Test the loginUser method of the UserService for failure
     * This test checks if the user is not logged in successfully
     * and if the user is not saved in the database
     * This test is expected to fail
     * The test will pass if the loginUser method throws an exception
     */

    @Test
    void testLoginUser_PasswordMismatch() {
        // Arrange
        String email = "user@example.com";
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        User foundUser = new User();
        foundUser.setPassword(encoder.encode(correctPassword));

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(foundUser));
        when(encoder.matches(wrongPassword, foundUser.getPassword())).thenReturn(false);

        // Act
        Optional<User> result = userService.loginUser(email, wrongPassword);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository).findUserByEmail(email);
        verify(encoder).matches(wrongPassword, foundUser.getPassword());
    }

    /**
     * Test the getUsersWithHighCalorieConsumption method of the UserService
     * This test checks if the users with high calorie consumption are retrieved successfully
     * and if the users are saved in the database
     */
    @Test
    void testGetUsersWithHighCalorieConsumption() {

        // Arrange
        List<User> highCalorieUsers = Arrays.asList(new User(), new User()); // Mock users

        when(userRepository.getUsersWithHighCalorieConsumption()).thenReturn(highCalorieUsers);

        // Act
        List<User> result = userService.getUsersWithHighCalorieConsumption();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(highCalorieUsers.size(), result.size());
        verify(userRepository).getUsersWithHighCalorieConsumption();
    }


}