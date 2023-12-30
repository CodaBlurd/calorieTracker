package org.coda.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class MealTest {
        private static final Logger log = LoggerFactory.getLogger(MealTest.class);
        @Mock
        private User user;
        @Mock
        private User user2;
        @Mock
        private Meal meal;
        @Mock
        private Meal meal2;
        @Mock
        private Food food;
        @Mock
        private Food food2;
        @Mock
        private Food.Nutrition nutrition;
        @Mock
        private Food.Nutrition nutrition2;

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

                when(user2.getId()).thenReturn(2L);
                when(user2.getName()).thenReturn("chris");
                when(user2.getPassword()).thenReturn("password");
                when(user2.getAge()).thenReturn(24);
                when(user2.getWeight()).thenReturn(60);
                when(user2.getHeight()).thenReturn(180.0);
                when(user2.getCalorieGoal()).thenReturn(2000);

                // meal mocks
                when(meal.getId()).thenReturn(1L);
                when(meal.getName()).thenReturn("Breakfast");
                when(meal.getUser()).thenReturn(user);
                when(meal.getFoods()).thenReturn(List.of(food));
                when(meal.getTotalCalories()).thenReturn(100.0);

                when(meal2.getId()).thenReturn(2L);
                when(meal2.getName()).thenReturn("Breakfast");
                when(meal2.getUser()).thenReturn(user2);
                when(meal2.getFoods()).thenReturn(List.of(food2));
                when(meal2.getTotalCalories()).thenReturn(100.0);

                // food mocks
                when(food.getId()).thenReturn(1L);
                when(food.getName()).thenReturn("Apple");
                when(food.getDetails()).thenReturn("Red Apple");
                when(food.getCalories()).thenReturn(100.0);

                when(food2.getId()).thenReturn(2L);
                when(food2.getName()).thenReturn("Orange");
                when(food2.getDetails()).thenReturn("Orange");
                when(food2.getCalories()).thenReturn(100.0);

                // nutrition mocks
                when(nutrition.getCarbohydrates()).thenReturn(10F);
                when(nutrition.getFat()).thenReturn(10F);
                when(nutrition.getProtein()).thenReturn(10F);

                when(nutrition2.getCarbohydrates()).thenReturn(10F);
                when(nutrition2.getFat()).thenReturn(10F);
                when(nutrition2.getProtein()).thenReturn(10F);
        }

        @Test
        void testMealWithValidValues() {
                // Arrange
                Meal meal = new Meal("Breakfast", user, List.of(food));

                // Act
                meal.setId(1);
                meal.setName("Breakfast");
                meal.setUser(user);
                meal.setFoods(List.of(food));
                meal.setTotalCalories(100.0);

                // Assert
                assertEquals(1, meal.getId());
                assertEquals("Breakfast", meal.getName());
                assertEquals(user, meal.getUser());
                assertEquals(List.of(food), meal.getFoods());
                assertEquals(100.0, meal.getTotalCalories());
        }
        @Test
    void testMealWithInvalidValues(){
            // Arrange
            Meal meal = new Meal("Breakfast", user, List.of(food));

            // Act
            meal.setId(1);
            meal.setName("Breakfast");
            meal.setUser(user);
            meal.setFoods(List.of(food));
            meal.setTotalCalories(100.0);

            // Assert
           assertNotEquals(2, meal.getId());
           assertNotEquals("Lunch", meal.getName());
           assertNotEquals(user2, meal.getUser());
           assertNotEquals(List.of(food2), meal.getFoods());
           assertNotEquals(200.0, meal.getTotalCalories());

        }

        @Test
        void testEqualsAndHashCode(){
            // Arrange
            Meal meal = new Meal("Breakfast", user, List.of(food));
            Meal meal2 = new Meal("Breakfast", user, List.of(food));

            // Act
            meal.setId(1);
            meal.setName("Breakfast");
            meal.setUser(user);
            meal.setFoods(List.of(food));
            meal.setTotalCalories(100.0);

            meal2.setId(1);
            meal2.setName("Breakfast");
            meal2.setUser(user);
            meal2.setFoods(List.of(food));
            meal2.setTotalCalories(100.0);

            // Assert
            assertEquals(meal, meal2);
            assertEquals(meal.hashCode(), meal2.hashCode());
        }






}