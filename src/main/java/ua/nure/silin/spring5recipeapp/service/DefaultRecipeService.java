package ua.nure.silin.spring5recipeapp.service;

import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.command.RecipeCommand;
import ua.nure.silin.spring5recipeapp.converter.RecipeCommandToRecipeConverter;
import ua.nure.silin.spring5recipeapp.converter.RecipeToRecipeCommandConverter;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;

import java.util.List;

import static java.lang.String.format;

@Service
public class DefaultRecipeService implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeCommandToRecipeConverter recipeCommandToRecipe;
    private RecipeToRecipeCommandConverter recipeToRecipeCommand;

    public DefaultRecipeService(RecipeRepository recipeRepository,
                                RecipeCommandToRecipeConverter recipeCommandToRecipe,
                                RecipeToRecipeCommandConverter recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes()
    {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(format("Recipe not found! Id: %d", id)));
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe savedRecipe = save(recipeCommandToRecipe.convert(recipeCommand));
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand getRecipeCommandById(Long id) {
        Recipe recipe = getRecipeById(id);
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
