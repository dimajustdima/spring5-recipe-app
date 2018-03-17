package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;

import static java.lang.String.format;

@Component
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter;
    private RecipeRepository recipeRepository;

    public IngredientCommandToIngredientConverter(UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter,
                                                  RecipeRepository recipeRepository) {
        this.uomConverter = uomConverter;
        this.recipeRepository = recipeRepository;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        Ingredient ingredient = new Ingredient(source.getDescription(), source.getAmount(),
                uomConverter.convert(source.getUom()));
        ingredient.setId(source.getId());
        ingredient.setRecipe(getRecipe(source.getRecipeId()));
        return ingredient;
    }

    private Recipe getRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException(format("Recipe with id %d not found.", recipeId)));
    }
}
