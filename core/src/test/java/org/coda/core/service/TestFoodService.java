package org.coda.core.service;

import org.coda.core.entities.Food;
import org.coda.core.entities.Meal;
import org.coda.core.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestFoodService {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;

    private Food food;

    private Meal meal;

    private List<Food> foods;

    /**
     * @beforeEach annotation to create test food and meal object before each test
     * @Mock annotation to mock the FoodRepository dependency
     * @InjectMocks annotation to inject the FoodService dependency
     *
     */



    @BeforeEach
    void setUp() {
        food = new Food();
        meal = new Meal();
        meal.setName("Breakfast");

        food.setName("Apple");
        food.setCalories(95);
        Food.Nutrition nutrition = new Food.Nutrition();
        nutrition.setCarbohydrates(25);
        nutrition.setProtein(0.5f);
        nutrition.setFat(0.3f);
        food.setNutrition(nutrition);
        food.setDetails("An apple a day keeps the doctor away");
        food.setMeals(List.of(meal));

        foods = new ArrayList<>();

        foods.add(food);


    }


    /**
     * @Test annotation to test the saveFood method
     * this test is to check if the saveFood method is called once
     * and the food object is saved successfully
     * return void
     */

    @Test
    void testSaveFood() {
        //Arrange
        when(foodRepository.save(food)).thenReturn(food);

        //Act
        foodService.saveFood(food);

        //Assert

        verify(foodRepository, times(1)).save(food);

    }

    /**
     * @Test annotation to test the saveAllFoods method
     * this test is to check if the saveAllFoods method is called once
     * and the foods collection is saved successfully
     * return void
     */

    @Test
    void testSaveAllFoods() {
        //Arrange
        when(foodRepository.saveAll(foods)).thenReturn(foods);

        //Act
        foodService.saveAllFoods(foods);

        //Assert
        verify(foodRepository, times(1)).saveAll(foods);


    }




    /**
     * @Test annotation to test the findAllFoodsWithHighCalories method
     * @Positive annotation to validate the threshold value. constraint: threshold > 0
     * return List<Food> if the foods are found
     * otherwise return empty List of foods
     */

    @Test
    void testFindAllFoodsWithHighCalories() {
        //Arrange
        when(foodRepository.findAllFoodsWithHighCalories(1000)).thenReturn(foods);

        //Act
        foodService.findFoodWithHighCalories(1000);

        //Assert
        verify(foodRepository, times(1)).findAllFoodsWithHighCalories(1000);

    }

    /**
     * @Test annotation to test the getAllFoods method
     * return List<Food> if the foods are found
     * otherwise return empty List of foods
     */

    @Test
    void testGetAllFoods() {
        //Arrange
        when(foodRepository.findAll()).thenReturn(foods);

        //Act
       List<Food> retrievedFoods = foodService.getAllFoods();

            //Assert
            verify(foodRepository, times(1)).findAll();
            assert(retrievedFoods.size() == 1);
            assert(retrievedFoods.get(0).getName().equals("Apple"));


    }

    /**
     * @Test annotation to test the getFoodById method
     * return Food if the food is found
     * otherwise return null
     */

    @Test
    void testGetFoodById() {
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(food));

        //Act
        Food retrievedFood = foodService.getFoodById(1L);

        //Assert
        verify(foodRepository, times(1)).findById(1L);
        assertEquals(retrievedFood.getName(), "Apple");
        assertEquals(retrievedFood.getCalories(), 95);
        assertEquals(retrievedFood.getDetails(), "An apple a day keeps the doctor away");
        assertEquals(retrievedFood.getNutrition().getCarbohydrates(), 25);
    }

    /**
     * Test method to update a food
     * return void
     */

    @Test
    void testUpdateFood() {
        //Arrange
        when(foodRepository.save(food)).thenReturn(food);

        //Act
     Food updatedFood = foodService.updateFood(food);

        //Assert
        verify(foodRepository, times(1)).save(food);
        assertEquals(updatedFood.getName(), "Apple");
        assertEquals(updatedFood.getCalories(), 95);
        assertEquals(updatedFood.getDetails(), "An apple a day keeps the doctor away");
        assertEquals(updatedFood.getNutrition().getCarbohydrates(), 25);
    }

    /**
     * Test method to delete a food by id
     * return void
     */

    @Test
    void testDeleteById() {
        //Arrange
        doNothing().when(foodRepository).deleteById(1L);

        //Act
        foodService.deleteFoodById(1L);

        //Assert
        verify(foodRepository, times(1)).deleteById(1L);

    }

    /**
     * Test method to delete all foods
     * return void
     */

    @Test
    void testDeleteAllFoods() {
        //Arrange
        doNothing().when(foodRepository).deleteAll();

        //Act
        foodService.deleteAllFoods();

        //Assert
        verify(foodRepository, times(1)).deleteAll();

    }

}
