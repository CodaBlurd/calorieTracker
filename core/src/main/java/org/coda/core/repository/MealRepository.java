package org.coda.core.repository;

import org.coda.core.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    /**
     * Method to find all meals with high calories
     * @param threshold: the threshold of the calories
     * @Transactional annotation to read the meals from the database
     * and commit all changes to database or rollback if error occurs
     * @return List<Meal> if the meals are found
     * otherwise return empty List of meals
     */
    @Query("SELECT m FROM Meal m WHERE m.calculateTotalMealCalories() > :threshold")
    List<Meal> findAllMealsWithHighCalories(@Param("threshold") int threshold);

    /**
     * Method to find all meals with low calories
     * @param threshold: the threshold of the calories
     * @Transactional annotation to read the meals from the database
     * and commit all changes to database or rollback if error occurs
     * @return List<Meal> if the meals are found
     * otherwise return empty List of meals
     */

    @Query("SELECT m FROM Meal m WHERE m.calculateTotalMealCalories() < :threshold")
    List<Meal> findAllMealsWithLowCalories(@Param("threshold") int threshold);


}
