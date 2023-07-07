package com.example.springboot.ex04;


import com.example.springboot.ex03.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MealService {

        private MealDAO mealDao;

        @Autowired
        public MealService(MealDAO mealDao) {
            this.mealDao = mealDao;
        }

        public List<Meal> getMealList() {
            return mealDao.getMeals();
        }

        public void addMeal(Meal meal) {
            if (meal == null) {
                throw new IllegalArgumentException("meal cannot be null");
            }
            mealDao.addMeal(meal);
        }

        public void updateMeal(String name, Meal updatedMeal) {
            Meal meal = getMealByName(name);
            if (meal != null) {
                meal.setName(updatedMeal.getName());
                meal.setDescription(updatedMeal.getDescription());
                meal.setPrice(updatedMeal.getPrice());
            }
        }


        public void deleteMeal(String name) {
            mealDao.removeMeal(name);
        }

        public void deleteMealsAbovePrice(double price) {
            List<Meal> mealsToRemove = new ArrayList<>();
            for (Meal meal : mealDao.getMeals()) {
                if (meal.getPrice() > price) {
                    mealsToRemove.add(meal);
                }
            }
            mealDao.getMeals().removeAll(mealsToRemove);
        }

        public void updateMealPrice(String name, double updatedPrice) {
            Meal existingMeal = getMealByName(name);
            if (existingMeal != null) {
                existingMeal.setPrice(updatedPrice);
                mealDao.updateMeal(existingMeal);
            }
        }

        private Meal getMealByName(String name) {
            for (Meal meal : mealDao.getMeals()) {
                if (meal.getName().equals(name)) {
                    return meal;
                }
            }
            return null;
        }
    }

