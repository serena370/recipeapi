package com.example.recipeapi.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data // Generates getters, setters, toString, equals, and hashCode

@Document(collection = "recipes")
public class Recipe {
    @Id
    private String id;

    @NotBlank
    private String title;

    @NotEmpty
    private List<String> ingredients;

    @NotBlank
    private String instructions;

    private int cookingTime;

    @NotBlank
    private String category;
}