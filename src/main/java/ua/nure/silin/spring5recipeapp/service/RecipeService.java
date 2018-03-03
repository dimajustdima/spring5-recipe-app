package ua.nure.silin.spring5recipeapp.service;

import ua.nure.silin.spring5recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe save(Recipe recipe);
    List<Recipe> getAllRecipes();
}
