package org.coda.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CalorieIntakeTest {
    @Mock
    private User user;

    @Mock
    private Meal meal;

    @Mock
    private Food food;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock behavior for user, meal, and food objects

        // user mocks
        when(user.getId()).thenReturn(1L);
        when(user.getName()).thenReturn("chris");
        when(user.getPassword()).thenReturn("password");
        when(user.getAge()).thenReturn(24);
        when(user.getWeight()).thenReturn(60);
        when(user.getHeight()).thenReturn(180.0);
        when(user.getCalorieGoal()).thenReturn(2000);

        // meal mocks
        when(meal.getId()).thenReturn(1L);
        when(meal.getName()).thenReturn("Breakfast");
        when(meal.getUser()).thenReturn(user);
        when(meal.getFoods()).thenReturn(List.of(food));
        when(meal.getTotalCalories()).thenReturn(100.0);

        // food mocks
        when(food.getId()).thenReturn(1L);
        when(food.getName()).thenReturn("Apple");
        when(food.getDetails()).thenReturn("Red Apple");
        when(food.getCalories()).thenReturn(100.0);




    }

    @Test
    void testCalorieIntakeWithValidValues() {
        // Arrange
        CalorieInTake calorieIntake = new CalorieInTake(meal, user, food);

        // Act
        calorieIntake.setId(1);
        calorieIntake.setMeal(meal);
        calorieIntake.setUser(user);
        calorieIntake.setFood(food);

        // Assert
        assertEquals(1, calorieIntake.getId());
        assertEquals(1, calorieIntake.getMeal().getId());
        assertEquals(1, calorieIntake.getUser().getId());
        assertEquals(1, calorieIntake.getFood().getId());
    }

    @Test
    void testCalorieIntakeWithInvalidValues() {
        // Arrange
        CalorieInTake calorieIntake = new CalorieInTake(meal, user, food);

        // Act
        calorieIntake.setId(1);
        calorieIntake.setMeal(meal);
        calorieIntake.setUser(user);
        calorieIntake.setFood(food);

        // Assert
        assertNotEquals(2, calorieIntake.getId());
        assertNotEquals(2, calorieIntake.getMeal().getId());
        assertNotEquals(2, calorieIntake.getUser().getId());
        assertNotEquals(2, calorieIntake.getFood().getId());
    }

    @Test
    void testEqualsAndHashCode(){
        // Arrange
        CalorieInTake calorieIntake = new CalorieInTake(meal, user, food);
        CalorieInTake calorieIntake2 = new CalorieInTake(meal, user, food);

        // Act
        calorieIntake.setId(1);
        calorieIntake.setMeal(meal);
        calorieIntake.setUser(user);
        calorieIntake.setFood(food);

        calorieIntake2.setId(1);
        calorieIntake2.setMeal(meal);
        calorieIntake2.setUser(user);
        calorieIntake2.setFood(food);

        // Assert
        assertEquals(calorieIntake, calorieIntake2);
        assertEquals(calorieIntake.hashCode(), calorieIntake2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentValues(){
        // Arrange
        CalorieInTake calorieIntake = new CalorieInTake(meal, user, food);
        CalorieInTake calorieIntake2 = new CalorieInTake(meal, user, food);

        // Act
        calorieIntake.setId(1);
        calorieIntake.setMeal(meal);
        calorieIntake.setUser(user);
        calorieIntake.setFood(food);

        calorieIntake2.setId(2);
        calorieIntake2.setMeal(meal);
        calorieIntake2.setUser(user);
        calorieIntake2.setFood(food);

        // Assert
        assertNotEquals(calorieIntake, calorieIntake2);
        assertNotEquals(calorieIntake.hashCode(), calorieIntake2.hashCode());
    }

}
