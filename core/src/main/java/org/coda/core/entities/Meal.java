package org.coda.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "meal")
@Getter
@Setter
public class Meal {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private  User user;

    @EqualsAndHashCode.Include
    @ManyToMany
    private List<Food> foods;

    private final LocalDateTime dateTime;

    private double totalCalories;

    // == constructor ==
    public Meal() {
        this.dateTime = LocalDateTime.now();
    }

    public Meal(String name, User user, List<Food> foods) {
        this.name = name;
        this.user = user;
        this.foods = foods;
        this.dateTime = LocalDateTime.now();

    }

    // == methods ==
    private double calculateTotalMealCalories(){
        double totalCalories = 0.0;
        for (Food food : foods) {
            totalCalories += food.getCalories();
        }
        return totalCalories;
    }

    public void setTotalCalories() {
        this.totalCalories = calculateTotalMealCalories();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return id == meal.id &&
                Double.compare(meal.totalCalories, totalCalories) == 0 &&
                Objects.equals(name, meal.name) &&
                Objects.equals(user, meal.user) &&
                Objects.equals(foods, meal.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, foods, totalCalories);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", totalCalories=" + totalCalories +
                '}';
    }
}
