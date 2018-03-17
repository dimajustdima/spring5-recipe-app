package ua.nure.silin.spring5recipeapp.service;

import ua.nure.silin.spring5recipeapp.command.IngredientCommand;

public interface IngredientService {
    IngredientCommand getIngredientCommandByRecipeAndIngredientIds(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredient(Long recipeId, Long ingredientId);
}
