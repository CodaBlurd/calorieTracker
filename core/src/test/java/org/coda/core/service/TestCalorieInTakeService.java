package org.coda.core.service;

import org.coda.core.entities.CalorieInTake;
import org.coda.core.repository.CalorieInTakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCalorieInTakeService {
    @Mock
    private CalorieInTakeRepository calorieInTakeRepository;

    @InjectMocks
    private CalorieInTakeService calorieInTakeService;

    private CalorieInTake calorieInTake;
    private long userId;
    private int month;

    /**
     * @beforeEach annotation to create test calorieInTake object before each test
     * @Mock annotation to mock the CalorieInTakeRepository dependency
     * @InjectMocks annotation to inject the CalorieInTakeService dependency
     *
     */

    @BeforeEach
    void setUp() {
        // Initialize your CalorieInTake and other variables here
        calorieInTake = new CalorieInTake();
        userId = 1L;
        month = 5; // Example month
        // Set other necessary fields of calorieInTake
    }

    /**
     * @Test annotation to test the saveCalorieInTake method
     * @doNothing() method to mock the save method of CalorieInTakeRepository
     * @when() method to mock the saveCalorieInTake method of CalorieInTakeService
     * @verify() method to verify that the save method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void saveCalorieInTake_Success() {
        when(calorieInTakeRepository.save(any(CalorieInTake.class))).thenReturn(calorieInTake);

        calorieInTakeService.saveCalorieInTake(calorieInTake);

        verify(calorieInTakeRepository, times(1)).save(calorieInTake);
    }

    /**
     * @Test annotation to test the updateCalorieInTake method
     * @doNothing() method to mock the save method of CalorieInTakeRepository
     * @when() method to mock the updateCalorieInTake method of CalorieInTakeService
     * @verify() method to verify that the save method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void updateCalorieInTake_Success() {
        when(calorieInTakeRepository.save(any(CalorieInTake.class))).thenReturn(calorieInTake);

        calorieInTakeService.updateCalorieInTake(calorieInTake);

        verify(calorieInTakeRepository, times(1)).save(calorieInTake);
    }

    /**
     * @Test annotation to test the getCalorieInTakeById method
     * @when() method to mock the getCalorieInTakeById method of CalorieInTakeService
     * @verify() method to verify that the findById method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void getCalorieInTakeById_Success() {
        when(calorieInTakeRepository.findById(anyLong())).thenReturn(Optional.of(calorieInTake));

        CalorieInTake result = calorieInTakeService.getCalorieInTakeById(userId);

        assertNotNull(result);
        assertEquals(calorieInTake, result);
    }

    /**
     * @Test annotation to test the getAllCalorieInTake method
     * @when() method to mock the getAllCalorieInTake method of CalorieInTakeService
     * @verify() method to verify that the findAll method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void getAllCalorieInTake_Success() {
        when(calorieInTakeRepository.findAll()).thenReturn(List.of(calorieInTake));

        List<CalorieInTake> result = calorieInTakeService.getAllCalorieInTake();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    /**
     * @Test annotation to test the getCalorieInTakeByUserId method
     * @when() method to mock the getCalorieInTakeByUserId method of CalorieInTakeService
     * @verify() method to verify that the findAllCalorieIntakeByUser method of CalorieInTakeRepository is called
     * returns void
     */

    @Test
    void getCalorieInTakeByUserId_Success() {
        when(calorieInTakeRepository.findAllCalorieIntakeByUser(anyLong())).thenReturn(List.of(calorieInTake));

        List<CalorieInTake> result = calorieInTakeService.getCalorieInTakeByUserId(userId);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    /**
     * @Test annotation to test the getCalorieInTakeByUserAndMonth method
     * @when() method to mock the getCalorieInTakeByUserAndMonth method of CalorieInTakeService
     * @verify() method to verify that the findAllCalorieIntakeByUserAndMonth method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void getCalorieInTakeByUserAndMonth_Success() {
        when(calorieInTakeRepository.findAllCalorieIntakeByUserAndMonth(anyLong(), anyInt())).thenReturn(List.of(calorieInTake));

        List<CalorieInTake> result = calorieInTakeService.getCalorieInTakeByUserAndMonth(userId, month);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }




    /**
     * @Test annotation to test the deleteCalorieInTake method
     * @doNothing() method to mock the deleteById method of CalorieInTakeRepository
     * @when() method to mock the deleteCalorieInTake method of CalorieInTakeService
     * @verify() method to verify that the deleteById method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void deleteCalorieInTake_Success() {
        doNothing().when(calorieInTakeRepository).deleteById(anyLong());

        calorieInTakeService.deleteCalorieInTake(userId);

        verify(calorieInTakeRepository, times(1)).deleteById(userId);
    }

    /**
     * @Test annotation to test the deleteAllCalorieInTake method
     * @doNothing() method to mock the deleteAll method of CalorieInTakeRepository
     * @when() method to mock the deleteAllCalorieInTake method of CalorieInTakeService
     * @verify() method to verify that the deleteAll method of CalorieInTakeRepository is called
     * return void
     */

    @Test
    void deleteAllCalorieInTake_Success() {
        doNothing().when(calorieInTakeRepository).deleteAll();

        calorieInTakeService.deleteAllCalorieInTake();

        verify(calorieInTakeRepository, times(1)).deleteAll();
    }






}
