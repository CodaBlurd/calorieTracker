package org.coda.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "food")
@Getter
@Setter
public class Food {
    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String details;

    @Embedded
    private Nutrition nutrition;



    private double calories;

    @ManyToMany(mappedBy = "foods")
    private List<Meal> meals;

    public Food() {} // == constructor ==

    public Food(String name, String details, Nutrition nutrition, double calories) {
        this.name = name;
        this.details = details;
        this.nutrition = nutrition;
        this.calories = calories;
    }


    // == nested class ==

    @Embeddable
    @Getter
    @Setter
    @ToString
     public static class Nutrition {
        private float protein;
        private float fat;
        private float carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return id == food.id && Double.compare(food.calories, calories) == 0
                && Objects.equals(name, food.name) && Objects.equals(details, food.details)
                && Objects.equals(nutrition, food.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, details, nutrition, calories);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", nutrition=" + nutrition +
                ", calories=" + calories +
                '}';
    }
}

