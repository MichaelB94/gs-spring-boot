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
            new Meal("Pizza","Semplicemente Margherita: con pomodoro fresco e fiordilatte",10),
            new Meal("Tagliere misto","Selezione dei migliori salumi italiani",7.50)
            );

    @GetMapping("/meals")
    public List<Meal> getMeals() {
        return meals;
    }

    @GetMapping("/meals/{name}")
    public ResponseEntity<?> getMealName (@PathVariable String name) {
        for(Meal meal : meals){
            if(meal.getName().equals(name)){
                return new ResponseEntity<Meal>(meal, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Meal not found.",HttpStatus.NOT_FOUND);
    }
}
