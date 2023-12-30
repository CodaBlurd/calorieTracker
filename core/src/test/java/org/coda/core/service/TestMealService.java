package org.coda.core.service;

import org.coda.core.entities.Food;
import org.coda.core.entities.Meal;
import org.coda.core.entities.User;
import org.coda.core.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for MealService.
 * This class tests various functionalities of the MealService including
 * meal creation, updating, and deleting.
 */
@ExtendWith(MockitoExtension.class)
class TestMealService {
    @Mock
    MealRepository mealRepository;

    @InjectMocks
    MealService mealService;

    private Meal meal;

    private Food food;

    private Food food2;

    private User user;

    private User user2;



    /**
     * Set up a meal object before each test
     * This meal object will be used in the tests
     * @BeforeEach annotation to run the setUp method before each test
     * @user: the user to be used in the tests
     * @user2: the user to be used in the tests
     * @meal: the meal to be used in the tests
     * @food: the food to be used in the tests
     * @food2: the food to be used in the tests
     *
     */

    @BeforeEach
    void setUp() {
        user = new User();
        user2 = new User();
        user.setId(1);
        user2.setId(2);
        user.setName("testUser");
        user2.setName("testUser2");

        meal = new Meal();
        food = new Food();
        food2 = new Food();

        food.setName("testFood");
        food.setId(1);
        food.setCalories(100);
        Food.Nutrition nutrition = new Food.Nutrition();
        nutrition.setCarbohydrates(10);
        nutrition.setFat(10);
        nutrition.setProtein(10);
        food.setNutrition(nutrition);
        food.setMeals(List.of(meal));


        meal.setName("testMeal");
        meal.setId(1);
        meal.setFoods(List.of(food));
        meal.setTotalCalories();
        meal.setUser(user);

    }

    /**
     * Test saveAllMeals method of the MealService
     * This test checks if all meals are created successfully
     * and if all meals are saved in the database
     * @Param meals: the meals to be saved
     */

    @Test
    void testSaveAllMeals_Success() {
        //Arrange
        when(mealRepository.saveAll(anyList())).thenReturn(List.of(meal));

        //Act
        mealService.saveAllMeals(List.of(meal));

        //Assert
        verify(mealRepository, times(1)).saveAll(anyList());
    }

    /**
     * Test saveAllMeals method of the MealService for failure
     * This test checks if all meals are not created successfully
     * and if all meals are not saved in the database
     * This test is expected to fail
     * The test will pass if the saveAllMeals method throws an exception
     * @Param meals: the meals to be saved
     */

    void testSaveAllMeals_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).saveAll(anyList());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.saveAllMeals(List.of(meal)));
        verify(mealRepository, times(1)).saveAll(anyList());
    }



    /**
     * Test the saveMeal method of the MealService
     * This test checks if the meal is created successfully
     * and if the meal is saved in the database
     */

    @Test
    void testCreateMeal_Success() {
        //Arrange
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);

        //Act
        Optional<Meal> result = mealService.saveMeal(meal);

        //Assert
        assertNotNull(result.get());
        assertEquals(meal, result.get());
        verify(mealRepository, times(1)).save(any(Meal.class));

    }

    /**
     * Test the createMeal method of the MealService for failure
     * This test checks if the meal is not created successfully
     * and if the meal is not saved in the database
     * This test is expected to fail
     * The test will pass if the createMeal method throws an exception
     */

    @Test
    void testCreateMeal_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).save(any(Meal.class));

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.saveMeal(meal));
        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    /**
     * Test the updateMeal method of the MealService
     * This test checks if the meal is updated successfully
     * and if the meal is saved in the database
     */

    @Test
    void testUpdateMeal_Success() {
        //Arrange
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);

        //Act
        boolean result = mealService.updateMeal(meal);

        //Assert
        assertTrue(result);
        verify(mealRepository, times(1)).save(any(Meal.class));



    }

    /**
     * Test the updateMeal method of the MealService for failure
     * This test checks if the meal is not updated successfully
     * and if the meal is not saved in the database
     * This test is expected to fail
     * The test will pass if the updateMeal method throws an exception
     */

    @Test
    void testUpdateMeal_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).save(any(Meal.class));

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.updateMeal(meal));
        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    /**
     * Test the deleteMeal by id method of the MealService
     * This test checks if the meal by the specified ID is deleted successfully
     * and if the meal is deleted from the database
     * @Param id: the id of the meal to be deleted
     */

    @Test
    void testDeleteMealById_Success() {
        //Arrange
        doNothing().when(mealRepository).deleteById(anyLong());

        //Act
        mealService.deleteMealById(1);

        //Assert
        verify(mealRepository, times(1)).deleteById(anyLong());
    }

    /**
     * Test the deleteMeal by id method of the MealService for failure
     * This test checks if the meal by the specified ID is not deleted successfully
     * and if the meal is not deleted from the database
     * This test is expected to fail
     * The test will pass if the deleteMealById method throws an exception
     * @Param id: the id of the meal to be deleted
     */

    @Test
    void testDeleteMealById_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).deleteById(anyLong());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.deleteMealById(1));
        verify(mealRepository, times(1)).deleteById(anyLong());
    }

    /**
     * Test DeleteAllMeals method of the MealService
     * This test checks if all meals are deleted successfully
     * and if all meals are deleted from the database
     */

    @Test
    void testDeleteAllMeals_Success() {
        //Arrange
        doNothing().when(mealRepository).deleteAll();

        //Act
        mealService.deleteAllMeals();

        //Assert
        verify(mealRepository, times(1)).deleteAll();
    }

    /**
     * Test DeleteAllMeals method of the MealService for failure
     * This test checks if all meals are not deleted successfully
     * and if all meals are not deleted from the database
     * This test is expected to fail
     * The test will pass if the deleteAllMeals method throws an exception
     */

    @Test
    void testDeleteAllMeals_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).deleteAll();

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.deleteAllMeals());
        verify(mealRepository, times(1)).deleteAll();
    }

    /**
     * Test the getAllMealsWithHighCalories method of the MealService
     * This test checks if all meals with high calories are returned successfully
     * and if all meals with high calories are returned from the database
     */

    @Test
    void testGetAllMealsWithHighCalories_Success() {
        //Arrange
        when(mealRepository.findAllMealsWithHighCalories(anyInt())).thenReturn(List.of(meal));

        //Act
        List<Meal> result = mealService.findHighCalorieMeals(100);

        //Assert
        assertNotNull(result);
        assertEquals(List.of(meal), result);
        verify(mealRepository, times(1)).findAllMealsWithHighCalories(anyInt());
    }

    /**
     * Test the getAllMealsWithHighCalories method of the MealService for failure
     * This test checks if all meals with high calories are not returned successfully
     * and if all meals with high calories are not returned from the database
     * This test is expected to fail
     * The test will pass if the getAllMealsWithHighCalories method throws an exception
     */

    @Test
    void testGetAllMealsWithHighCalories_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).findAllMealsWithHighCalories(anyInt());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.findHighCalorieMeals(100));
        verify(mealRepository, times(1)).findAllMealsWithHighCalories(anyInt());
    }

    /**
     * Test the getAllMealsWithLowCalories method of the MealService
     * This test checks if all meals with low calories are returned successfully
     * and if all meals with low calories are returned from the database
     */

    @Test
    void testGetAllMealsWithLowCalories_Success() {
        //Arrange
        when(mealRepository.findAllMealsWithLowCalories(anyInt())).thenReturn(List.of(meal));

        //Act
        List<Meal> result = mealService.findLowCalorieMeals(100);

        //Assert
        assertNotNull(result);
        assertEquals(List.of(meal), result);
        verify(mealRepository, times(1)).findAllMealsWithLowCalories(anyInt());
    }

    /**
     * Test the getAllMealsWithLowCalories method of the MealService for failure
     * This test checks if all meals with low calories are not returned successfully
     * and if all meals with low calories are not returned from the database
     * This test is expected to fail
     * The test will pass if the getAllMealsWithLowCalories method throws an exception
     */

    @Test
    void testGetAllMealsWithLowCalories_Fail() {
        //Arrange
        doThrow(RuntimeException.class).when(mealRepository).findAllMealsWithLowCalories(anyInt());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> mealService.findLowCalorieMeals(100));
        verify(mealRepository, times(1)).findAllMealsWithLowCalories(anyInt());
    }







}
