package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MealsController {
    public List<Meal> meals = Arrays.asList(
            new Meal("Pasta Pesto","Deliziose linguine al pesto di basilico",12.99),
            new Meal("Pizza","Semplicemente Margherita : con pomodoro fresco e fiordilatte",10),
            new Meal("Tagliere misto","Selezione dei migliori salumi italiani",7.50)
            );

    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return meals;
    }

    @GetMapping("/meals/{name}")
    public ResponseEntity<?> getMealName (@PathVariable String name) {
        for(Meal meal : meals){
            if(meal.getName().toLowerCase().equals(name.toLowerCase())){
                return new ResponseEntity<Meal>(meal, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Meal not found.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meals/description-match/{description}")
    public ResponseEntity<?> getMealDescription (@PathVariable String description) {
        for(Meal meal : meals){
            if(meal.getDescription().toLowerCase().contains(description.toLowerCase())){
                return new ResponseEntity<Meal>(meal, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Meal not found.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/meals/price")
    public ResponseEntity<?> getMealPrice (@RequestParam("lowerPrice") Double lowerPrice,@RequestParam("upperPrice") Double upperPrice) {
        List<Meal> mealInRange = new ArrayList<Meal>();
        for(Meal meal : meals){
            if(lowerPrice < meal.getPrice() && meal.getPrice() < upperPrice){
                mealInRange.add(meal);
            }
        }
        if (!mealInRange.isEmpty()) {
            return new ResponseEntity<List<Meal>>(mealInRange, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No meals found within the specified price range.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/meals")
    public ResponseEntity<String> addMeal(@RequestBody Meal newMeal) {
        meals.add(newMeal);
        return ResponseEntity.ok("New meal added");
    }

    @PutMapping("/meals/{name}")
    public ResponseEntity<String> updateMeal(@PathVariable String name, @RequestBody Meal updatedMeal) {
        for (Meal meal : meals) {
            if (meal.getName().equals(name)) {
                meal.setName(updatedMeal.getName());
                meal.setDescription(updatedMeal.getDescription());
                meal.setPrice(updatedMeal.getPrice());
                return ResponseEntity.ok("Meal updated");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/meals/{name}")
    public ResponseEntity<String> deleteMeal(@PathVariable String name) {
        for (Meal meal : meals) {
            if (meal.getName().equals(name)) {
                meals.remove(meal);
                return ResponseEntity.ok("Meal deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/meals/upper_to/{price}")

    public ResponseEntity<String> deleteMealsAbovePrice(@PathVariable double price) {
        List<Meal> mealsToRemove = new ArrayList<>();
        for (Meal meal : meals) {
            if (meal.getPrice() > price) {
                mealsToRemove.add(meal);
            }
        }
        meals.removeAll(mealsToRemove);
        return ResponseEntity.ok("Meal deleted");
    }

    @PutMapping("/update/meals_price/{name}")
    public ResponseEntity<String> updateMealPrice(@PathVariable String name, @RequestBody Double updatedPrice) {
        for (Meal meal : meals) {
            if (meal.getName().equals(name)) {
                meal.setPrice(updatedPrice);
                return ResponseEntity.ok("Meal price updated");
            }
        }
        return ResponseEntity.notFound().build();

    }
}

