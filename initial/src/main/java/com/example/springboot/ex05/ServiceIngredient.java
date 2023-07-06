package com.example.springboot.ex05;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public class ServiceIngredient {
    private RepositoryIngredient ingredientRepository;

    @Autowired
    public ServiceIngredient (RepositoryIngredient ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<EntityIngredient> getAllIngredients() {
        return (List<EntityIngredient>) ingredientRepository.findAll();
    }

    public Optional<EntityIngredient> getIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }

    public EntityIngredient saveIngredient(EntityIngredient ingredient) {
        return (EntityIngredient) ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}

