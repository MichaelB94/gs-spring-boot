package com.example.springboot.ex05.controller;

import com.example.springboot.ex05.model.EntityIngredient;
import com.example.springboot.ex05.service.ServiceIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/ingredients")
    public class ControllerIngredient {
        private ServiceIngredient serviceIngredient;

        @Autowired
        public ControllerIngredient(ServiceIngredient serviceIngredient) {
            this.serviceIngredient = serviceIngredient;
        }

        @GetMapping("/getall")
        public List<EntityIngredient> getAllIngredients() {
            List<EntityIngredient> ingredients = serviceIngredient.getAllIngredients();
            return ingredients;
        }

        @GetMapping("get/{id}")
        public ResponseEntity<EntityIngredient> getIngredientById(@PathVariable Long id) {
            Optional<EntityIngredient> ingredient = serviceIngredient.getIngredientById(id);
            return ingredient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping("/register")
        public ResponseEntity<EntityIngredient> createIngredient(@RequestBody EntityIngredient ingredient) {
            EntityIngredient savedIngredient = serviceIngredient.saveIngredient(ingredient);
            return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
            serviceIngredient.deleteIngredient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
