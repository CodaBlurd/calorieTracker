package org.coda.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class FoodTest {
    @Mock
    private Food food;
    @Mock
    private Meal meal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock behavior for food objects
        when(food.getId()).thenReturn(1L);
        when(food.getName()).thenReturn("Apple");
        when(food.getDetails()).thenReturn("Red Apple");
        when(food.getCalories()).thenReturn(100.0);

        // Mock behavior for meal objects
        when(meal.getId()).thenReturn(1L);
        when(meal.getName()).thenReturn("Breakfast");
        when(meal.getFoods()).thenReturn(List.of(food));
        when(meal.getTotalCalories()).thenReturn(100.0);



    }

    @Test
    void testFoodWithValidValues() {
        // Arrange
        Food food = new Food();
        food.setId(1);
        food.setName("Apple");
        food.setDetails("Red Apple");
        food.setCalories(100);
        Food.Nutrition nutrition = new Food.Nutrition();
        nutrition.setCarbohydrates(10);
        nutrition.setFat(10);
        nutrition.setProtein(10);
        food.setNutrition(nutrition);

        // Act
        Long id = food.getId();
        String name = food.getName();
        String details = food.getDetails();
        Double calories = food.getCalories();
        Food.Nutrition nutrition1 = food.getNutrition();

        // Assert
        assertEquals(1, id);
        assertEquals("Apple", name);
        assertEquals("Red Apple", details);
        assertEquals(100, calories);
        assertEquals(10, nutrition1.getCarbohydrates());
        assertEquals(10, nutrition1.getFat());
        assertEquals(10, nutrition1.getProtein());
    }

    @Test
    void testFoodWithInvalidValues() {
        // Arrange
        Food food = new Food();
        food.setId(1);
        food.setName("Apple");
        food.setDetails("Red Apple");
        food.setCalories(100);
        Food.Nutrition nutrition = new Food.Nutrition();
        nutrition.setCarbohydrates(10);
        nutrition.setFat(10);
        nutrition.setProtein(10);
        food.setNutrition(nutrition);

        // Act
        Long id = food.getId();
        String name = food.getName();
        String details = food.getDetails();
        Double calories = food.getCalories();
        Food.Nutrition nutrition1 = food.getNutrition();

        // Assert
        assertNotEquals(2, id);
        assertNotEquals("Orange", name);
        assertNotEquals("Orange", details);
        assertNotEquals(200, calories);
        assertNotEquals(20, nutrition1.getCarbohydrates());
        assertNotEquals(20, nutrition1.getFat());
        assertNotEquals(20, nutrition1.getProtein());

    }




}
