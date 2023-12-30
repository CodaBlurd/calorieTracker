package org.coda.core.service;

import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.CalorieInTake;
import org.coda.core.repository.CalorieInTakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class CalorieInTakeService {

    private final CalorieInTakeRepository calorieInTakeRepository;

    /**
     * Constructor based dependency injection
     *
     * @param calorieInTakeRepository for CRUD operations and custom queries.
     * @Autowired annotation to inject the CalorieInTakeRepository
     * dependency
     */

    @Autowired
    public CalorieInTakeService(CalorieInTakeRepository calorieInTakeRepository) {
        this.calorieInTakeRepository = calorieInTakeRepository;
    }

    /**
     * Method to save calorieIntake
     *
     * @Param calorieInTake: the calorieInTake object to be saved
     * @Valid annotation to validate the calorieInTake object
     * @Transactional annotation to save the calorieInTake for the database as a transaction
     * return void
     */
    @Transactional
    public void saveCalorieInTake(@Valid CalorieInTake calorieInTake) {
        calorieInTakeRepository.save(calorieInTake);
        log.info("CalorieInTake created successfully: {}", calorieInTake);
    }

    /**
     * Method to update calorieIntake
     * @Param calorieInTake: the calorieInTake object to be updated
     * @Valid annotation to validate the calorieInTake object
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to update the calorieInTake for the database as a transaction
     * return void
     */
    @Modifying
    @Transactional
    public void updateCalorieInTake(@Valid CalorieInTake calorieInTake) {
        calorieInTakeRepository.save(calorieInTake);
        log.info("CalorieInTake updated successfully: {}", calorieInTake);
    }

    /**
     * Method to delete calorieIntake
     * @Param id: the id of the calorieIntake to be deleted
     * @Valid annotation to validate the calorieInTake object
     * @Valid annotation to validate the calorieInTake object
     * @Transactional annotation to delete the calorieInTake for the database as a transaction
     * return void
     */
    @Modifying
    @Transactional
    public void deleteCalorieInTake(@Valid long id) {
        calorieInTakeRepository.deleteById(id);
        log.info("CalorieInTake deleted successfully: {}", id);
    }

    /**
     * Method to delete all calorieIntake
     * @Modifying annotation to indicate that the query is an update or delete query
     * @Transactional annotation to delete the calorieInTake for the database as a transaction
     * return void
     */

    @Modifying
    @Transactional
    public void deleteAllCalorieInTake() {
        calorieInTakeRepository.deleteAll();
    }

    /**
     * Method to get calorieIntake by id
     * @Param id: the id of the calorieInTake to be retrieved
     * @Valid annotation to validate the calorieInTake object
     * @Transactional annotation to read the calorieInTake from the database as a transaction
     * @return CalorieInTake if the calorieInTake is found otherwise return null
     */

    @Transactional(readOnly = true)
    public CalorieInTake getCalorieInTakeById(@Valid long id) {
        return calorieInTakeRepository.findById(id).orElse(null);
    }

    /**
     * Method to get all calorieIntake
     * @Transactional annotation to read the calorieInTake from the database as a transaction
     * @return List<CalorieInTake> if the calorieInTake are found otherwise return empty List of calorieInTake
     */

    @Transactional(readOnly = true)
    public List<CalorieInTake> getAllCalorieInTake() {
        return calorieInTakeRepository.findAll();
    }

    /**
     * Method to get calorieIntake by user id
     * @Param id: the id of the user
     * @Valid annotation to validate the calorieInTake object
     * @Transactional annotation to read the calorieInTake from the database as a transaction
     * @return List<CalorieInTake> if the calorieInTake are found otherwise return empty List of calorieInTake
     */

    @Transactional(readOnly = true)
    public List<CalorieInTake> getCalorieInTakeByUserId(@Valid long id) {
        return calorieInTakeRepository.findAllCalorieIntakeByUser(id);
    }

    /**
     * Get calorieIntake by User and month
     * @param id: the id of the user
     * @param month: the month of the calorieInTake
     * @return List<CalorieInTake> if the calorieInTake are found otherwise return empty List of calorieInTake
     */

    @Transactional(readOnly = true)
    public List<CalorieInTake> getCalorieInTakeByUserAndMonth(@Valid long id, @Valid int month) {
        return calorieInTakeRepository.findAllCalorieIntakeByUserAndMonth(id, month);
    }


}
