package ua.nure.silin.spring5recipeapp.service;

import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;

import java.util.List;

import static java.lang.String.format;

@Service
public class DefaulteRecipeService implements RecipeService {

    private RecipeRepository recipeRepository;

    public DefaulteRecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}
