package com.example.springboot.ex4;

import com.example.springboot.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewMealController {

    private MealService mealService;
    @Autowired
    public NewMealController(MealService mealService) {
        this.mealService = mealService;
    }

        @GetMapping("/mealsList")
        public List<Meal> getMeals() {
            return mealService.getMealList();
        }

        @PostMapping("/meal")
        public ResponseEntity<String> addMeal(@RequestBody Meal newMeal) {
            mealService.addMeal(newMeal);
            return ResponseEntity.ok("New meal added");
        }

        @PutMapping("/put/meal/{name}")
        public ResponseEntity<String> updateMeal(@PathVariable String name, @RequestBody Meal updatedMeal) {
            mealService.updateMeal(name, updatedMeal);
            return ResponseEntity.ok("Meal updated");
        }
        @DeleteMapping("/delete/meal/{name}")
        public ResponseEntity<String> deleteMeal(@PathVariable String name) {
            mealService.deleteMeal(name);
            return ResponseEntity.ok("Meal deleted");
        }

        @DeleteMapping("/delete/meal/price/{price}")
        public ResponseEntity<String> deleteMealsAbovePrice(@PathVariable double price) {
            mealService.deleteMealsAbovePrice(price);
            return ResponseEntity.ok("Meals removed");
        }

        @PutMapping("/update/meal/{name}/price")
        public ResponseEntity<String> updateMealPrice(@PathVariable String name, @RequestBody Double updatedPrice) {
            mealService.updateMealPrice(name, updatedPrice);
            return ResponseEntity.ok("Meal price updated");
        }
    }

