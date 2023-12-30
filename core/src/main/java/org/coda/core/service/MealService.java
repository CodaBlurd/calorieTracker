package org.coda.core.service;
import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.Meal;
import org.coda.core.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MealService {
    private final MealRepository mealRepository;

    /**
     * Constructor based dependency injection
     *
     * @param mealRepository for CRUD operations and custom queries
     * @Autowired annotation to inject the MealRepository dependency
     */

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    /**
     * Method to save a meal
     *
     * @param meal: the meal to be saved
     * @return Meal Object if the meal is saved successfully otherwise return Optional meal object
     * @Valid annotation to validate the meal object
     * @Transactional annotation to save the meal for the database as a transaction
     */

    public Optional<Meal> saveMeal(@Valid Meal meal){
         Meal savedMeal = mealRepository.save(meal);
        log.info("Meal created successfully {}", meal);
        return Optional.of(savedMeal);
    }

    /**
     * Method to save all meals
     * @param meals: the meals to be saved constraint:
     * meals != null && !meals.isEmpty()
     * @Transactional annotation to save the meals
     * for the database as a single transaction
     * return List<Meal> if the meals are saved successfully
     * */

    @Transactional
    public List<Meal> saveAllMeals(List<Meal> meals) {
    List<Meal> savedMeals = mealRepository.saveAll(meals);
        log.info("Meals created successfully: {}", meals);
        return savedMeals;
    }

    /**
    method to update a meal
    @param meal: the meal to be updated
    @Valid annotation to validate the meal object
    @Modifying annotation to indicate that the query is an update or delete query
    @Transactional annotation to update the meal for the database as a transaction
    * */
    @Modifying
    @Transactional
    public boolean updateMeal(@Valid Meal meal) {
        mealRepository.save(meal);
        log.info("Meal updated successfully: {}", meal);
        return true;
    }

    /**
     * Method to find all meals with high calories
     *
     * @param threshold: the threshold of the calories
     * @return List<Meal> if the meals are found
     * otherwise return empty List of meals
     * @Positive annotation to validate the threshold value. constraint: threshold > 0
     * @Transactional annotation to read the meals from the database
     * and commit all changes to database or rollback if error occurs
     */
    @Transactional(readOnly = true)
    public List<Meal> findHighCalorieMeals(@Positive int threshold) {
        return mealRepository.findAllMealsWithHighCalories(threshold);

    }

    /**
     * Method to find all meals with low calories
     *
     * @param threshold: the threshold of the calories
     * @return List<Meal> if the meals are found
     * otherwise return empty List of meals
     * @Positive annotation to validate the threshold value. constraint: threshold > 0
     * @Transactional annotation to read the meals from the database
     * and commit all changes to database or rollback if error occurs
     */
    @Transactional(readOnly = true)
    public List<Meal> findLowCalorieMeals(@Positive int threshold) {
        return mealRepository.findAllMealsWithLowCalories(threshold);

    }

    /**
     * method to find all meals
     *
     * @return List<Meal> if the meals are found
     * otherwise return empty List of meals
     * @Transactional annotation to read the meals from the database
     * as part of a single transaction
     */

    @Transactional(readOnly = true)
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    /**
     * Method to find a meal by id
     *
     * @param id the id of the meal
     * @return Optional<Meal> if the meal is found
     * otherwise return empty Optional meal object
     * @Valid annotation to validate the meal id
     * @Transactional annotation to read the meal from the
     * database as part of a single transaction
     */

    @Transactional(readOnly = true)
    public Meal getMealById(@Valid long id) {
        return mealRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete a meal by id
     *
     * @param id the id of the meal to delete
     * return void, return nothing
     * @Valid annotation to validate the meal id
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to delete the meal from the
     * database as part of a single transaction
     */
    @Modifying
    @Transactional
    public void deleteMealById(@Valid long id) {
        mealRepository.deleteById(id);
        log.info("Meal deleted successfully: {}", id);
    }

    /**
     * Method to delete all meals
     * return void, return nothing
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to delete the meals from the
     * database as part of a single transaction
     */
    @Modifying
    @Transactional
    public void deleteAllMeals() {
        mealRepository.deleteAll();
        log.info("Meals deleted successfully");
    }





}
