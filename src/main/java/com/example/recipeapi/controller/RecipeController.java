package com.example.recipeapi.controller;

import com.example.recipeapi.model.Recipe;
import com.example.recipeapi.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@Validated  // Enables validation for the controller
public class RecipeController {

    private final RecipeService service;

    // Constructor Injection
    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }
    // Create a new recipe
    @PostMapping
    public ResponseEntity<Recipe> create(@Valid @RequestBody Recipe r) {
        Recipe createdRecipe = service.create(r);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);  // Returns 201 Created
    }

    // Get all recipes
    @GetMapping
    public List<Recipe> getAll() {
        return service.getAll();
    }

    // Get a specific recipe by its ID
    @GetMapping("/{id}")
    public Recipe getById(@PathVariable String id) {
        return service.getById(id);  // Throws exception if not found
    }

    // Update an existing recipe by its ID
    @PutMapping("/{id}")
    public Recipe update(@PathVariable String id, @Valid @RequestBody Recipe r) {
        return service.update(id, r);  // Returns the updated recipe
    }

    // Delete a recipe by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);  // Calls the service to delete the recipe
        return ResponseEntity.noContent().build();  // Returns 204 No Content
    }


    // Get recipes by category (Using findByCategory)
    @GetMapping("/category/{category}")
    public List<Recipe> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);  // Fetch recipes filtered by category
    }



}
