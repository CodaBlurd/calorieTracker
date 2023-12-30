package org.coda.core.repository;

import org.coda.core.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM food f WHERE f.calories > :threshold")
    List<Food> findAllFoodsWithHighCalories(int threshold);




}
