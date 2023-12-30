package org.coda.consoleUI;

import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.User;
import org.coda.core.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class UserMenu {
    private final MainMenu mainMenu = new MainMenu();
    protected Scanner scanner = new Scanner(System.in);

    private UserService userService;

    public UserMenu(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display the user menu.
     *
     */
    public void displayUserMenu() {
        log.info("Displaying user menu...");

        log.info("1. Create user");
        log.info("2. Update user");
        log.info("3. Delete user");
        log.info("4. Display user");
        log.info("5. Display all users");
        log.info("6. Return to main menu");

        log.info("Please enter a number between 1 and 6.");
        String choice = scanner.nextLine();

        switch(choice){
            case "1" -> createUser();
            case "2" -> updateUser();
            case "3" -> deleteUser();
            case "4" -> displayUser();
            case "5" -> displayAllUsers();
            case "6" -> mainMenu.displayMainMenu();
        }



    }

    /**
     * Create a user method.
     * reads input using scanner and creates a user object
     * calls the create user method from the user service
     * navigates back to the main menu
     * catches any exceptions and logs them
     * returns void
     *
     */

    public void createUser() {
        User user = new User();
        User.Contact contact = new User.Contact();
        log.info("Enter your name: ");
        String name = scanner.nextLine();
        log.info("Enter your password: ");
        String password = scanner.nextLine();
        log.info("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        log.info("Enter your email: ");
        String email = scanner.nextLine();
        log.info("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        contact.setEmail(email);
        contact.setPhone(phoneNumber);
        user.setContact(contact);

        try {
            userService.createUser(user);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
        }
    }

    public void updateUser() {
        User user = new User();
        User.Contact contact = new User.Contact();
        log.info("Enter your name: ");
        String name = scanner.nextLine();
        log.info("Enter your password: ");
        String password = scanner.nextLine();
        log.info("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        log.info("Enter your email: ");
        String email = scanner.nextLine();
        log.info("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        contact.setEmail(email);
        contact.setPhone(phoneNumber);
        user.setContact(contact);

        try {
            userService.updateUser(user);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
        }

    }

    public void deleteUser() {

    }

    public void displayUser() {

    }

    public void displayAllUsers() {

    }
}
