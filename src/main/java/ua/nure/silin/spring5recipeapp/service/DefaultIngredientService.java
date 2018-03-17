package ua.nure.silin.spring5recipeapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.converter.IngredientCommandToIngredientConverter;
import ua.nure.silin.spring5recipeapp.converter.IngredientToIngredientCommandConverter;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.IngredientRepository;

import java.util.Set;

import static java.lang.String.format;

@Service
public class DefaultIngredientService implements IngredientService {

    private RecipeService recipeService;
    private IngredientRepository ingredientRepository;
    private IngredientToIngredientCommandConverter ingredientToCommandConverter;
    private IngredientCommandToIngredientConverter commandToIngredientConverter;

    public DefaultIngredientService(RecipeService recipeService,
                                    IngredientRepository ingredientRepository,
                                    IngredientToIngredientCommandConverter ingredientToCommandConverter, IngredientCommandToIngredientConverter commandToIngredientConverter) {
        this.recipeService = recipeService;
        this.ingredientRepository = ingredientRepository;
        this.ingredientToCommandConverter = ingredientToCommandConverter;
        this.commandToIngredientConverter = commandToIngredientConverter;
    }

    @Override
    public IngredientCommand getIngredientCommandByRecipeAndIngredientIds(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToCommandConverter::convert)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        format("Ingredient with id %d does not exist for recipe with id %d", ingredientId, recipeId)));
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        //TODO: refactor this method to avoid recipe fetching here
        Recipe recipe = recipeService.getRecipeById(ingredientCommand.getRecipeId());
        return recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .map(ingredient -> updateAndSaveIngredient(ingredientCommand))
                .findFirst()
                .orElseGet(() -> createAndSaveIngredient(ingredientCommand));
    }

    private IngredientCommand updateAndSaveIngredient(IngredientCommand ingredientCommand) {
        Ingredient ingredient = commandToIngredientConverter.convert(ingredientCommand);
        return ingredientToCommandConverter.convert(ingredientRepository.save(ingredient));
    }

    private IngredientCommand createAndSaveIngredient(IngredientCommand ingredientCommand) {
        Ingredient ingredient = commandToIngredientConverter.convert(ingredientCommand);
        Recipe recipe = ingredient.getRecipe();
        recipe.getIngredients().add(ingredient);
        recipeService.save(recipe);
        return ingredientToCommandConverter.convert(ingredient);
    }

    @Override
    public void deleteIngredient(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        Set<Ingredient> recipeIngredients = recipe.getIngredients();

        recipeIngredients.stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst()
                .ifPresent(recipeIngredients::remove);

        recipeService.save(recipe);
        ingredientRepository.deleteById(ingredientId);
    }
}
