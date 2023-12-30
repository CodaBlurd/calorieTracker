package org.coda.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class UserTest {
    @Mock
    private User user;
    @Mock
    private User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(user.getId()).thenReturn(1L);
        when(user.getName()).thenReturn("chris");
        when(user.getPassword()).thenReturn("password");
        when(user.getAge()).thenReturn(24);
        when(user.getWeight()).thenReturn(60);
        when(user.getHeight()).thenReturn(180.0);
        when(user.getCalorieGoal()).thenReturn(2000);

        when(user1.getId()).thenReturn(1L);
        when(user1.getName()).thenReturn("chris");
        when(user1.getPassword()).thenReturn("password");
        when(user1.getAge()).thenReturn(24);
        when(user1.getWeight()).thenReturn(60);
        when(user1.getHeight()).thenReturn(180.0);
        when(user1.getCalorieGoal()).thenReturn(2000);
    }

    @Test
    void testUserWithValidValues() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("chris");
        user.setPassword("password");
        user.setAge(24);
        user.setWeight(60);
        user.setHeight(180.0);
        user.setCalorieGoal(2000);

        // Act
        Long id = user.getId();
        String name = user.getName();
        String password = user.getPassword();
        int age = user.getAge();
        int weight = user.getWeight();
        double height = user.getHeight();
        int calorieGoal = user.getCalorieGoal();

        // Assert
        assertEquals(1L, id);
        assertEquals("chris", name);
        assertEquals("password", password);
        assertEquals(24, age);
        assertEquals(60, weight);
        assertEquals(180.0, height);
        assertEquals(2000, calorieGoal);
    }

    @Test
    void testUserWithInvalidValues() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("chris");
        user.setPassword("password");
        user.setAge(24);
        user.setWeight(60);
        user.setHeight(180.0);
        user.setCalorieGoal(2000);

        // Act
        Long id = user.getId();
        String name = user.getName();
        String password = user.getPassword();
        int age = user.getAge();
        int weight = user.getWeight();
        double height = user.getHeight();
        int calorieGoal = user.getCalorieGoal();

        // Assert
    assertNotEquals(2L, id);
    assertNotEquals("chris1", name);
    assertNotEquals("password1", password);
    assertNotEquals(25, age);
    assertNotEquals(61, weight);
    assertNotEquals(181.0, height);
    assertNotEquals(2001, calorieGoal);

    }

    @Test
    void testEqualsAndHashCode(){
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setName("chris");
    user.setPassword("password");
    user.setAge(24);
    user.setWeight(60);
    user.setHeight(180.0);
    user.setCalorieGoal(2000);

    User user1 = new User();
    user1.setId(1L);
    user1.setName("chris");
    user1.setPassword("password");
    user1.setAge(24);
    user1.setWeight(60);
    user1.setHeight(180.0);
    user1.setCalorieGoal(2000);

    // Act
    boolean result = user.equals(user1);
    int hashcode1 = user.hashCode();
    int hashcode2 = user1.hashCode();

    // Assert
    assertEquals(true, result);
    assertEquals(hashcode1, hashcode2);


    }







}