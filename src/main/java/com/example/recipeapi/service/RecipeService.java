package com.example.recipeapi.service;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class RecipeService {


    @Autowired
    private RecipeRepository repository;



    // Create a new recipe
    public Recipe create(Recipe r) {
        // Generate a custom short ID (e.g., using UUID)
        String customId = UUID.randomUUID().toString().substring(0, 8);  // Shorten UUID to 8 characters
        r.setId(customId);  // Set custom ID
        return repository.save(r);  // Save with the custom ID
    }

    // Get all recipes from the database
    public List<Recipe> getAll() {
        return repository.findAll();
    }

    // Get a specific recipe by ID
    public Recipe getById(String id) {
        // If the recipe is not found, throw an exception with a message
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    // Update an existing recipe by its ID
    public Recipe update(String id, Recipe r) {
        // First, check if the recipe exists
        Recipe existing = getById(id);
        // Set the ID of the new recipe to the ID of the existing one
        r.setId(existing.getId());
        // Save the updated recipe
        return repository.save(r);
    }

    // Delete a recipe by ID
    public void delete(String id) {
        repository.deleteById(id);
    }

    // Get recipes by category
    public List<Recipe> getByCategory(String category) {
        return repository.findByCategory(category);  // Uses the findByCategory method in repository
    }
}
