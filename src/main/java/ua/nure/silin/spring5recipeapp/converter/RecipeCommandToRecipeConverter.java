package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.CategoryCommand;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.command.RecipeCommand;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.domain.Recipe.RecipeBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand, Recipe> {

    private IngredientCommandToIngredientConverter ingredientConverter;
    private CategoryCommandToCategoryConverter categoryConverter;
    private NotesCommandToNotesConverter notesConverter;

    public RecipeCommandToRecipeConverter(IngredientCommandToIngredientConverter ingredientConverter,
                                          CategoryCommandToCategoryConverter categoryConverter,
                                          NotesCommandToNotesConverter notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        Recipe recipe = new RecipeBuilder(
                source.getDescription(),
                source.getPrepTime(),
                source.getCookTime(),
                source.getServings(),
                source.getSource(),
                source.getDirections())
                .ofDifficulty(source.getDifficulty())
                .withUrl(source.getUrl())
                .withNotes(notesConverter.convert(source.getNotes()))
                .fromIngredients(convertIngredients(source.getIngredients()))
                .ofCategories(convertCategories(source.getCategories()))
                .build();
        recipe.setId(source.getId());
        return recipe;
    }

    private Set<Ingredient> convertIngredients(Set<IngredientCommand> ingredientCommands) {
        return ingredientCommands.stream()
                .map(ingredientConverter::convert)
                .collect(Collectors.toSet());
    }

    private Set<Category> convertCategories(Set<CategoryCommand> categoryCommands) {
        return categoryCommands.stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toSet());
    }
}
