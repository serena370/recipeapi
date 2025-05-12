package com.example.recipeapi.repository;

import com.example.recipeapi.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//This allows you to perform CRUD operations (like save(), findById(), findAll(), etc.) on Recipe entities,
// where the ID type is String (since MongoDB typically uses a String as the ID by default).

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByCategory(String category);

}