package org.coda.core.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "user_table")
@Getter
@Setter
@ToString
public class User {

    // == fields ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //== validation (mandatory fields)==
    @NotBlank(message = "Please input your name")
    private String name;

    @NotBlank(message = "Please input your password")
    private String password;

    @Min(value = 18, message = "You must be at least 18 years old")
    @Max(value = 100, message = "Age must be less than 100 years old")
    private int age;
    private int weight;
    private double height;

    private int calorieGoal;

    @Embedded
    private Contact contact;


    //== database mapping of the relationship between user and calorie intake ==
    @OneToMany(mappedBy = "user")
    private List<CalorieInTake> calorieInTakes = new ArrayList<>();

    //==database mapping of the relationship between user and meal one user can have many meals ==
    @OneToMany(mappedBy = "user")
    private List<Meal> meals = new ArrayList<>();



    // == constructor ==
    public User() {}

    public User(String name,
                String password, int age,
                int weight, double height,
                int calorieGoal, Contact contact) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.calorieGoal = calorieGoal;
        setContact(contact);
    }

    // == methods ==

    @Embeddable
    @Getter
    @Setter
    @ToString
    public static class Contact {
        private static final String UK_PHONE_NUMBER_REGEX = "^(\\+44\\s?7\\d{3}|\\(?07\\d{3}\\)?)\\s?\\d{3}\\s?\\d{3}$";
        @Email(message = "Please input a valid email")
        private String email;

        @Pattern(regexp = UK_PHONE_NUMBER_REGEX, message = "Invalid UK phone number")
        private String phone;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Contact)) return false;
            Contact contact = (Contact) o;
            return email.equals(contact.email) && phone.equals(contact.phone);
        }

        @Override
        public int hashCode() {
            return Objects.hash(email, phone);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && age == user.age && weight == user.weight
                && Double.compare(user.height, height) == 0
                && calorieGoal == user.calorieGoal
                && name.equals(user.name)
                && password.equals(user.password)
                && Objects.equals(contact, user.contact); // Include null check for Contact
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, age, weight, height, calorieGoal, contact);
    }



}
