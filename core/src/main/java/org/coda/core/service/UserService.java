package org.coda.core.service;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.User;
import org.coda.core.exceptions.UserNotFoundException;
import org.coda.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UserService{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    /**
     * Constructor based dependency injection
     * @param userRepository for CRUD operations
     * @param encoder to encode the user password
     * @Autowired annotation to inject the UserRepository
     * and BcryptPasswordEncoder dependency
     */
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    /**
     * Method to log in a user
     * @param email: the email of the user
     * @param password: the password of the user
     * @Email annotation to validate the user email
     * @NotEmpty annotation to validate the user password
     * @encoder.matches to check if the password matches the encoded password
     * @Transactional annotation to read the user from the database
     * @return Optional<User> if the user is found and the password matches
     * otherwise return empty Optional user object
     */

    @Transactional(readOnly = true)
    public Optional<User> loginUser(@Email String email, @NotEmpty String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent()){
            if(encoder.matches(password, user.get().getPassword())){
                log.info("User logged in successfully: {}", user);
                return user;
            }
        }
        return Optional.empty();
    }

    /**
     * Method to create a user
     * @param user the user to be created
     * @Valid annotation to validate the user object
     * @encoder.encode to encode the user password
     * @Transactional annotation to create the user as part of a single transaction
     * @return Optional<User> if the user is created successfully
     * otherwise return empty Optional user object
     */

    @Transactional
    public Optional<User> createUser(@Valid User user) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("User created successfully: {}", user);
            return Optional.of(user);

    }

    /**
     * Method to update a user
     * @param user the user to be updated
     * @Valid annotation to validate the user object
     * @encoder.encode to encode the user password
     * @Transactional annotation to update the user as part of a single transaction
     * @return Optional<User> if the user is updated successfully
     * otherwise return empty Optional user object
     */

    @Transactional
    public User updateUser(@Valid User user) {
        if (userRepository.existsById(user.getId())) {
            user.setPassword(encoder.encode(user.getPassword()));
            User updatedUser = userRepository.save(user);
            log.info("User updated successfully: {}", updatedUser);
            return updatedUser;
        } else {
            // Handle the case where the user does not exist.
            log.error("Attempted to update a non-existent user: {}", user);
            throw new UserNotFoundException("User not found with id: " + user.getId());
        }
    }



    /**
     * Method to get all users
     * @Transactional annotation to read the users
     * from the database as part of a single transaction
     * @return List<User> if the users are found
     * otherwise return empty collection
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
            List<User> users = userRepository.findAll();
            log.info("Users retrieved successfully: {}", users);
            return users;
    }

    /**
     * Method to get a user by id
     * @param id the id of the user
     * @Valid annotation to validate the user id
     * @Transactional annotation to read the user from the
     * database as part of a single transaction
     * @return Optional<User> if the user is found
     * otherwise return empty Optional user object
     */

    @Transactional
    public Optional<User> getUserById(@Valid long id) {
       return userRepository.findById(id);
    }

    /**
     * Method to find a user by email
     * @param email the email of the user
     * @Email annotation to validate the user email
     * @Transactional annotation to read the user from the
     * database as part of a single transaction
     * @return Optional<User> if the user is found
     */


    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(@Email String email) {
          return userRepository.findUserByEmail(email);
    }

    /**
     * Method to get users with high calories
     * @Transactional annotation to read the users from the database
     * @return List<User> if the users with high calories are found
     * otherwise return empty collection
     */

    @Transactional(readOnly = true)
    public List<User> getUsersWithHighCalorieConsumption() {
     return userRepository.getUsersWithHighCalorieConsumption();
    }

    /**
     * Method to get users with low calories
     * @Transactional annotation to read the users from the database
     * @return List<User> if the users with low calories are found
     * otherwise return empty collection
     */

    @Transactional
    public List<User> getUsersWithLowCalorieConsumption() {
        return userRepository.getUsersWithLowCalorieConsumption();
    }

    /**
     * Method to get users by role
     * @Transactional annotation to read the users from the database
     * @param role the role of the user
     * @return List<User> if the users with the role are found
     * otherwise return empty collection
     */

    @Transactional
    public List<User> getUsersByRole(String role) {
        return userRepository.getUsersByRole(role);
    }

    /**
     * Method to update user last login
     * @Transactional annotation to update the user last login in the database
     * @param id the id of the user, lastLogin: the last login timeStamp of the user
     * @Valid annotation to validate the user id
     * @return void
     */

    @Transactional
    public void updateUserLastLogin(@Valid long id) {
        userRepository.updateUserLastLogin(id, LocalDateTime.now().toString());
    }

    /**
     * Method to delete inactive users
     * @Transactional annotation to delete the inactive users from the database
     * @return boolean true if the users are deleted otherwise return false
     */

    @Transactional
    protected boolean deleteInactiveUsers() {
        return userRepository.deleteInactiveUsers(LocalDateTime.now().minusMonths(6).toString());
    }
}
