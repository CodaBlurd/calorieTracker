package org.coda.core.service;

import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.Food;
import org.coda.core.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@Service
public class FoodService{

private final FoodRepository foodRepository;

    /**
     * Constructor based dependency injection
     * @param foodRepository for CRUD operations and custom queries
     * @Autowired annotation to inject the FoodRepository dependency
     */

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Method to find all foods with high calories
     * @param threshold: the threshold of the calories
     * @Positive annotation to validate the threshold value. constraint: threshold > 0
     * @Transactional annotation to read the foods from the database
     * and commit all changes to database or rollback if error occurs
     * @return List<Food> if the foods are found
     * otherwise return empty List of foods
     */
    public List<Food> findFoodWithHighCalories(@Positive int threshold) {
        return foodRepository.findAllFoodsWithHighCalories(threshold);
    }

    /**
     * Method to save a food
     * @param food the food to be saved
     * @Valid annotation to validate the food object
     * @Transactional annotation to commit all changes to database or rollback if error occurs
     * return void
     */

    @Transactional
    public void saveFood(@Valid Food food) {
        foodRepository.save(food);
        log.info("Food created successfully: {}", food);

    }

    /**
     * Method to save all foods
     * @param foods the foods to be saved
     * @NotEmpty annotation to validate the foods collection
     * @Transactional annotation to commit all changes to database or rollback if error occurs
     * @return List<Food> if the foods are saved successfully.
     */
    @Transactional
    public List<Food> saveAllFoods(@NotEmpty List<Food> foods) {
        List<Food> savedFoods = foodRepository.saveAll(foods);
        log.info("Foods created successfully: {}", savedFoods.size());
        return savedFoods;
    }

    /**
     * Method to find all foods
     * @Transactional annotation to read the foods from the database
     * and commit all changes to database or rollback if error occurs
     * @return List<Food> if the foods are found
     * otherwise return empty List of foods
     */

    @Transactional(readOnly = true)
    public List<Food> getAllFoods() {
        List<Food> foods = foodRepository.findAll();
        log.info("Foods retrieved successfully: {}", foods);
        return foods;

    }

    /**
     * Method to find a food by id
     * @param id the id of the food
     * @Valid annotation to validate the food id
     * @Transactional annotation to read the food from the
     * database as part of a single transaction
     * @return Food if the food is found
     * otherwise return null
     */

    @Transactional(readOnly = true)
    public Food getFoodById(long id) {
        return foodRepository.findById(id).orElse(null);
    }

    /**
     * Method to update a food
     * @param food the food to be updated
     * @Valid annotation to validate the food object
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to commit all changes to database or rollback if error occurs
     * @return void
     */
    @Modifying
    @Transactional
    public Food updateFood(@Valid Food food) {
    Food updatedFood = foodRepository.save(food);
        log.info("Food updated successfully: {}", food);
        return updatedFood;

    }

    /**
     * Method to delete a food by id
     * @param id the id of the food
     * @Valid annotation to validate the food id
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to commit all changes to database or rollback if error occurs
     * return void
     */
    @Modifying
    @Transactional
    public void deleteFoodById(long id) {
        foodRepository.deleteById(id);

    }

    /**
     * Method to delete all foods
     * @Transactional annotation to commit all changes to database or rollback if error occurs
     * @Modifying annotation to indicate that the query is an update or delete query
     * return void
     */
    @Modifying
    @Transactional
    public void deleteAllFoods() {
        foodRepository.deleteAll();

    }
}
