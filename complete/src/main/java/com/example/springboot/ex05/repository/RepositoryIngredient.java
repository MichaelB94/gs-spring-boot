package com.example.springboot.ex05.repository;


import com.example.springboot.ex05.model.EntityIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryIngredient extends JpaRepository<EntityIngredient,Long> {


}
