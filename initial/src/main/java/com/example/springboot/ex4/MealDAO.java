package com.example.springboot.ex4;

import com.example.springboot.Meal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

    @Repository
    public class MealDAO {
        ArrayList<Meal> meals = new ArrayList<>();
        public MealDAO() {
            meals.add(new Meal("Pasta Pesto","Deliziose linguine al pesto di basilico",12.99));
            meals.add(new Meal("Pizza","Semplicemente Margherita : con pomodoro fresco e fiordilatte",10));
            meals.add(new Meal("Tagliere misto","Selezione dei migliori salumi italiani",7.50));
        }

        public void addMeal (Meal meal) {
            this.meals.add(meal);
        }

        public void removeMeal (String name) {
            this.meals.removeIf(meal -> meal.getName().equals(name));
        }

        public void updateMeal (Meal meal) {
            this.meals.removeIf(m -> m.getName().equals(meal.getName()));
            this.meals.add(meal);
        }

        public List<Meal> getMeals () {
            return this.meals;
        }
    }

