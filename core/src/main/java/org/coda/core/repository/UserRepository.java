package org.coda.core.repository;

import org.coda.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to find a user by email
     * @param email the email of the user
     * @Query annotation to write a custom query
     * @return Optional<User> if the user is found otherwise return empty Optional user object
     */
    @Query("SELECT u FROM User u WHERE u.contact.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    /**
     * method to find users with high calories consumption
     * @Query annotation to write a custom query
     * @return List<User> if the users with high calories are found otherwise return empty collection
     */
    @Query("SELECT u FROM User u WHERE u.calorieIntake > 2000")
    List<User> getUsersWithHighCalorieConsumption();

    /**
     * method to find users with low calories consumption
     * @Query annotation to write a custom query
     * @return List<User> if the users with low calories are found otherwise return empty collection
     */

    @Query("SELECT u FROM User u WHERE u.calorieIntake < 2000")
    List<User> getUsersWithLowCalorieConsumption();

    /**
     * method to find users by role
     * @Query annotation to write a custom query
     * @param role the role of the user
     * @return List<User> if the users with the role are found otherwise return empty collection
     */
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> getUsersByRole(@Param("role") String role);

    /**
     * method to update user last login
     * @Query annotation to write a custom query
     * @param id the id of the user, lastLogin: the last login timeStamp of the user
     * @Transactional to commit all changes to database or rollback if error occurs
     * @Modifying to indicate that the query is an update or delete query
     * @return void
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastLogin = :lastLogin WHERE u.id = :id")
    void updateUserLastLogin(@Param("id") long id, @Param("lastLogin") String lastLogin);

    /**
     * method to delete inactive users
     * @Query annotation to write a custom query
     * @param lastLogin the last login timeStamp of the user
     * @Transactional to commit all changes to database or rollback if error occurs
     * @Modifying to indicate that the query is an update or delete query
     * @return boolean true if the users are deleted otherwise return false
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.lastLogin < :lastLogin")
    boolean deleteInactiveUsers(@Param("lastLogin") String lastLogin);

    /**
     * method to login user
     * @Query annotation to write a custom query
     * @param email the email of the user
     * @return Optional<User> if the user is found otherwise return empty Optional user object
     */
    @Query("SELECT u FROM User u WHERE u.contact.email = :email")
    Optional<User> loginUser(@Param("email") String email);

}
