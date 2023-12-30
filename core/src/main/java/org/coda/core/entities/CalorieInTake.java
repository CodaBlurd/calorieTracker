package org.coda.core.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "calorie_intake")
@Getter
@Setter
@ToString
public class CalorieInTake {

    // == fields ==

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Meal meal;
    @ManyToOne
    private User user;
    @ManyToOne
    private Food food;
    private final LocalDateTime dateTime;

    // == constructor ==
    public CalorieInTake() {
        this.dateTime = LocalDateTime.now();
    }

    public CalorieInTake(Meal meal, User user, Food food) {
        this.meal = meal;
        this.user = user;
        this.food = food;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalorieInTake)) return false;
        CalorieInTake calorieInTake = (CalorieInTake) o;
        return id == calorieInTake.id && meal.equals(calorieInTake.meal)
                && user.equals(calorieInTake.user)
                && food.equals(calorieInTake.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meal, user, food);
    }

}
