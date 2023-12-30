package org.coda.core;
import lombok.extern.slf4j.Slf4j;
import org.coda.core.entities.Food;
import org.coda.core.entities.Meal;
import org.coda.core.service.FoodService;
import org.coda.core.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.coda.core, org.coda.core.service, org.coda.core.repository"})
@EnableJpaRepositories(basePackages = {"org.coda.core.repository"})

@Slf4j
public class Main {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private MealService mealService;

    @Autowired
    private FoodService foodService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Main main = context.getBean(Main.class);
        //== call main menu ==
        main.mainMenu();

        log.info("Meal saved successfully");



    }

    //== menu Items ==

    //== main menu ==
    public void mainMenu(){
        log.info("Welcome to Calorie Tracker");
        String choice = "";

        log.info("Please select an option from the menu below");
        log.info("1. Meal Menu");
        log.info("2. Food Menu");
        log.info("3. Exit");

        choice = scanner.nextLine();

        switch(choice){
            case "1":
                mealMenu();
                break;
            case "2":
                foodMenu();
                break;
            case "3":
                log.info("Thank you for using Calorie Tracker");
                break;
            default:
                log.info("Invalid choice");
                break;
        }
    }

    //== meal menu ==
    public void mealMenu(){
        log.info("Welcome to Calorie Tracker");
           String choice;
           //== meal menu options ==
           log.info("Menu options");
           log.info("Enter 1 to add a new meal");
           log.info("Enter 2 to update an existing meal");
           log.info("Enter 3 to delete an existing meal");
           log.info("Enter 4 to view all meals");
           log.info("Enter 5 to delete all meals");
           log.info("Enter 6 to exit");

           //== collect user input ==
            choice = scanner.nextLine();
            //== switch statement to handle user input ==
           switch (choice){
               case "1" -> addMealMenu();
               case "2" -> updateMealMenu();
               case "3" -> deleteMealMenu();
               case "4" -> viewAllMealsMenu();
               case "5" -> deleteAllMealsMenu();
               case "6" -> exitMealMenu();
           }
    }

    public void addMealMenu(){
    try{

        //== meal and food and subclass nutrition objects creation ==
        Meal meal = new Meal();
        List<Food> foods = new ArrayList<>();

        //== collect meal and food info from user ==
        log.info("Please enter the name of the meal");
        String name = scanner.nextLine();
        meal.setName(name);

        log.info("Enter number of Food items for meal");
        int numFood = scanner.nextInt();
        scanner.nextLine();
        //== loop to collect food info from user ==
        for(int i=0; i<numFood; i++){
            Food food = new Food();
            log.info("Please enter the food name");
            String foodName = scanner.nextLine();
            food.setName(foodName);
            log.info("Please enter the food details");
            String foodDetails = scanner.nextLine();
            food.setDetails(foodDetails);
            Food.Nutrition nutrition = new Food.Nutrition();
            log.info("please enter the food nutrition (carbohydrate) information");
            float carbohydrate = scanner.nextFloat();
            scanner.nextLine();
            nutrition.setCarbohydrates(carbohydrate);

            float fat = scanner.nextFloat();
            scanner.nextLine();
            nutrition.setFat(fat);

            float protein = scanner.nextFloat();
            scanner.nextLine();
            nutrition.setProtein(protein);
            food.setNutrition(nutrition);

            log.info("Please enter the food calories");
            double calories = scanner.nextDouble();
            scanner.nextLine();
            food.setCalories(calories);
            foods.add(food);
            food.setMeals(List.of(meal));

        }
        //== bulk save food objects ==
        foodService.saveAllFoods(foods);
        meal.setFoods(foods);
        mealService.saveMeal(meal);
        log.info("Meal saved successfully");

    } catch (Exception e) {
        log.info("Error occurred while saving meal");
        log.error(e.getMessage());


    }

    }

    public void updateMealMenu(){
       try{
           Meal meal = new Meal();
           List<Food> foods = new ArrayList<>();

           log.info("Please enter the meal name to update \n");
           String name = scanner.nextLine();

           if (name.isEmpty() || name.isBlank()){
               log.info("Please enter the meal name to update \n");
               name = scanner.nextLine();
           }
           meal.setName(name);


           log.info("Enter List of Food items for meal");
           int numFood = scanner.nextInt();
           scanner.nextLine();

           if (numFood < 0){
               log.info("Number of food items cannot be less than 0");
               numFood = scanner.nextInt();
               scanner.nextLine();
           }


           for (int i = 0; i < numFood; i++) {
               Food food = new Food();
               log.info("Please enter the food name");
               String foodName = scanner.nextLine();

               //== input validation ==
               if (foodName.isEmpty() || foodName.isBlank()){
                   log.info("Please enter the food name");
                   foodName = scanner.nextLine();
               }
                food.setName(foodName);

               String foodDetails = scanner.nextLine();
                if (foodDetails.isEmpty() || foodDetails.isBlank()){
                     log.info("Please enter the food details");
                     foodDetails = scanner.nextLine();
                }
               food.setDetails(foodDetails);

               log.info("please enter the food nutrition (carbohydrate) information");
               float carbohydrate = scanner.nextFloat();
               scanner.nextLine();
               if (carbohydrate < 0){
                   log.info("Food nutrition, carbohydrate cannot be less than 0 \n");
                   carbohydrate = scanner.nextFloat();
                   scanner.nextLine();
               }

               food.getNutrition().setCarbohydrates(carbohydrate);
               float fat = scanner.nextFloat();
               scanner.nextLine();
                if (fat < 0){
                     log.info("Food nutrition, fat cannot be less than 0 \n");
                     fat = scanner.nextFloat();
                     scanner.nextLine();
                }
               food.getNutrition().setFat(fat);
               float protein = scanner.nextFloat();
               scanner.nextLine();
                if (protein < 0){
                     log.info("Food nutrition, protein cannot be less than 0 \n");
                     protein = scanner.nextFloat();
                     scanner.nextLine();
                }
               food.getNutrition().setProtein(protein);
               log.info("Please enter the food calories");
               double calories = scanner.nextDouble();
               scanner.nextLine();
                if (calories < 0){
                     log.info("Food calories cannot be less than 0 \n");
                     calories = scanner.nextDouble();
                     scanner.nextLine();
                }
               food.setCalories(calories);
               foods.add(food);
               food.setMeals(List.of(meal));


           }
           foodService.saveAllFoods(foods);
           meal.setFoods(foods);
           mealService.updateMeal(meal);


           log.info("Meal updated successfully");

       } catch (Exception e) {
           log.info("Error occurred while updating meal");
           log.info(e.getMessage());
       }


    }

    public void deleteMealMenu(){
    try{
        log.info("Please enter the meal id to delete");
        long id = scanner.nextLong();
        scanner.nextLine();
        if (id < 0){
            log.info("Enter valid ID: Meal id cannot be less than 0");
            id = scanner.nextLong();
            scanner.nextLine();
        }
        mealService.deleteMealById(id);
        log.info("Meal deleted successfully");
    } catch (Exception e) {
        log.info("Error occurred while deleting meal");
        log.info(e.getMessage());
    }

        }

    public void viewAllMealsMenu(){
        try{
            List<Meal> meals = mealService.getAllMeals();
            meals.forEach(meal -> log.info(meal.toString()));
        } catch (Exception e) {
            log.info("Error occurred while viewing all meals");
            log.info(e.getMessage());
        }

    }

    public void deleteAllMealsMenu(){
        try{
            mealService.deleteAllMeals();
            log.info("All meals deleted successfully");
        } catch (Exception e) {
            log.info("Error occurred while deleting all meals");
            log.info(e.getMessage());
        }
    }

    public void exitMealMenu(){
       mainMenu();
    }





    //== food menu ==

    public void foodMenu(){
        log.info("Welcome to Calorie Tracker");
        String choice;
        //== food menu options ==
        log.info("Menu options");
        log.info("Enter 1 to add a new food");
        log.info("Enter 2 to update an existing food");
        log.info("Enter 3 to delete an existing food");
        log.info("Enter 4 to view all foods");
        log.info("Enter 5 to delete all foods");
        log.info("Enter 6 to exit");

        //== collect user input ==
        choice = scanner.nextLine();
        //== switch statement to handle user input ==
        switch (choice){
            case "1" -> addFoodMenu();
            case "2" -> updateFoodMenu();
            case "3" -> deleteFoodMenu();
            case "4" -> viewAllFoodsMenu();
            case "5" -> deleteAllFoodsMenu();
            case "6" -> exitFoodMenu();

        }
    }

    //== food menu options ==

    public void addFoodMenu(){
        Food food = new Food();
        log.info("Please enter the food information \n");

        log.info("Please enter the food name");
        String name = scanner.nextLine();
        food.setName(name);

        log.info("Please enter the food details");
        String details = scanner.nextLine();
        food.setDetails(details);

        log.info("please enter the food nutrition (carbohydrate) information");
        float carbohydrate = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setCarbohydrates(carbohydrate);

        float fat = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setFat(fat);

        float protein = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setProtein(protein);

        log.info("Please enter the food calories");
        double calories = scanner.nextDouble();
        scanner.nextLine();
        food.setCalories(calories);

        foodService.saveFood(food);
        log.info("Food saved successfully");

    }

    //== update food menu ==

    public void updateFoodMenu(){
        Food food = new Food();
        log.info("Please enter the food update information \n");

        log.info("Please enter the food name");
        String name = scanner.nextLine();
        food.setName(name);

        log.info("Please enter the food details");
        String details = scanner.nextLine();
        food.setDetails(details);

        log.info("please enter the food nutrition (carbohydrate) information");
        float carbohydrate = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setCarbohydrates(carbohydrate);

        float fat = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setFat(fat);

        float protein = scanner.nextFloat();
        scanner.nextLine();
        food.getNutrition().setProtein(protein);

        log.info("Please enter the food calories");
        double calories = scanner.nextDouble();
        scanner.nextLine();
        food.setCalories(calories);

        foodService.updateFood(food);
        log.info("Food updated successfully");
    }

//== get food menu ==
 public void getFoodMenu(){
        log.info("Please enter the food id to get");
        long id = scanner.nextLong();
        scanner.nextLine();
        Food food = foodService.getFoodById(id);
        log.info(food.toString());
    }

//== delete food menu ==
    public void deleteFoodMenu(){
            log.info("Please enter the food id to delete");
            long id = scanner.nextLong();
            scanner.nextLine();
            foodService.deleteFoodById(id);
            log.info("Food deleted successfully");
        }
        //== view all foods menu ==
        public void viewAllFoodsMenu(){
            List<Food> foods = foodService.getAllFoods();
            foods.forEach(food -> log.info(food.toString()));
        }
        //== delete all foods menu ==
        public void deleteAllFoodsMenu(){
            foodService.deleteAllFoods();
            log.info("All foods deleted successfully");
        }
//== exit food menu ==
        public void exitFoodMenu(){
            mainMenu();
        }

//== calorie intake menu














}
