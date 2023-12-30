package org.coda.core.repository;

import org.coda.core.entities.CalorieInTake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalorieInTakeRepository extends JpaRepository<CalorieInTake, Long> {
    /**
     * Method to find all calorieIntake by user id
     * @param userId: the id of the user
     * @Transactional annotation to read the calorieIntake from the database
     * @return List<CalorieInTake> if the calorieIntake are found
     */

    @Query("SELECT c FROM CalorieInTake c WHERE c.user.id = :userId")
    List<CalorieInTake> findAllCalorieIntakeByUser(@Param("id") long userId);

    /**
     * Method to find all calorieIntake by date
     * @param date: the date of the calorieIntake
     * @Transactional annotation to read the calorieIntake from the database
     * @return List<CalorieInTake> if the calorieIntake are found
     */

    @Query("SELECT c FROM CalorieInTake c WHERE c.date = :date")
    List<CalorieInTake> findAllCalorieIntakeByDate(@Param("date") LocalDate date);

    /**
     * Method to find all calorieIntake by user id and date
     * @param userId: the id of the user
     * @param date: the date of the calorieIntake
     * @Transactional annotation to read the calorieIntake from the database
     * @return List<CalorieInTake> if the calorieIntake are found
     */

    @Query("SELECT c FROM CalorieInTake c WHERE c.user.id = :userId AND c.date = :date")
    List<CalorieInTake> findAllCalorieIntakeByUserAndDate(@Param("id") long userId, @Param("date") LocalDate date);

    /**
     * Method to find all calorieIntake by user id and date range
     * @param userId: the id of the user
     * @param startDate: the start date of the calorieIntake
     * @param endDate: the end date of the calorieIntake
     * @Transactional annotation to read the calorieIntake from the database
     * @return List<CalorieInTake> if the calorieIntake are found
     */

    @Query("SELECT c FROM CalorieInTake c WHERE c.user.id = :userId AND c.date BETWEEN :startDate AND :endDate")
    List<CalorieInTake> findAllCalorieIntakeByUserAndDateRange(@Param("id") long userId,
    @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    /**
     * method to find all calorieIntake by user id and month.
     * @param userId: the id of the user
     * @param month: the month of the calorieIntake
     * @Transactional annotation to read the calorieIntake from the database
     * @return List<CalorieInTake> if the calorieIntake are found
     */

    @Query("SELECT c FROM CalorieInTake c WHERE c.user.id = :userId AND MONTH(c.date) = :month")
    List<CalorieInTake> findAllCalorieIntakeByUserAndMonth(@Param("id") long userId, @Param("month") int month);






}
