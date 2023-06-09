package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/meals/price/{lowerPrice}/{upperPrice}")
    public ResponseEntity<?> getMealPrice (@PathVariable Double lowerPrice,@PathVariable Double upperPrice) {
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
}

// 3 - In the method, add two query parameters "min" and "max" using the @RequestParam annotation.
// 4 - Return a list of Meal objects with prices within the specified range.