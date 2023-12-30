package org.coda.consoleUI;

import org.coda.core.entities.User;
import org.coda.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for UserMenu
 * Uses Mockito to mock the UserService dependency
 * Uses ArgumentCaptor to capture the User object passed to the UserService
 * Uses ByteArrayInputStream to mock user input
 * Uses Scanner to read user input
 * Uses JUnit 5
 * Uses MockitoExtension to use Mockito with JUnit 5
 * Uses @Mock to mock the UserService dependency
 * Uses @BeforeEach to set up the UserMenu object
 * Uses @Test to test the createUser and updateUser methods
 * Uses assertEquals to assert that the captured User object has the expected properties
 * Uses verify to verify that the UserService methods are called
 */

@ExtendWith(MockitoExtension.class)
class TestUserMenu {
    @Mock
    private UserService userService;

    private UserMenu userMenu;

    /**
     * Set up the UserMenu object
     * Set up the System.in input stream
     * Set up the Scanner object
     */

    @BeforeEach
    void setUp() {
        userMenu = new UserMenu(userService);
        System.setIn(new ByteArrayInputStream("John\npassword123\n25\njohn@example.com\n1234567890\n".getBytes()));
        userMenu.scanner = new Scanner(System.in);
    }

    /**
     * Test the createUser method
     * Use ArgumentCaptor to capture the User object passed to createUser
     * Use assertEquals to assert that the captured User object has the expected properties
     * Use verify to verify that the UserService methods are called
     */

    @Test
    void testCreateUser() {
        userMenu.createUser();

        // Use ArgumentCaptor to capture the User object passed to createUser
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).createUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        // Assert that the captured User object has the expected properties
        assertEquals("John", capturedUser.getName());
        assertEquals("password123", capturedUser.getPassword());
        assertEquals(25, capturedUser.getAge());
        assertEquals("john@example.com", capturedUser.getContact().getEmail());
        assertEquals("1234567890", capturedUser.getContact().getPhone());
    }

    /**
     * Test the updateUser method
     * Use ArgumentCaptor to capture the User object passed to updateUser
     * Use assertEquals to assert that the captured User object has the expected properties
     * Use verify to verify that the UserService methods are called
     */

    @Test
    void testUpdateUser() {
        userMenu.updateUser();

        // Use ArgumentCaptor to capture the User object passed to updateUser
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).updateUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        // Assert that the captured User object has the expected properties
        assertEquals("John", capturedUser.getName());
        assertEquals("password123", capturedUser.getPassword());
        assertEquals(25, capturedUser.getAge());
    }

}
