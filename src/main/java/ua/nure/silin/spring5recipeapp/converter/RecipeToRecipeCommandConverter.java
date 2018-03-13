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

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class RecipeToRecipeCommandConverter implements Converter<Recipe, RecipeCommand> {

    private NotesToNotesCommandConverter notesConverter;
    private CategoryToCategoryCommandConverter categoryConverter;
    private IngredientToIngredientCommandConverter ingredientConverter;

    public RecipeToRecipeCommandConverter(NotesToNotesCommandConverter notesConverter,
                                          CategoryToCategoryCommandConverter categoryConverter,
                                          IngredientToIngredientCommandConverter ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        recipeCommand.setCategories(convertCategories(source.getCategories()));
        recipeCommand.setIngredients(convertIngredients(source.getIngredients()));
        return recipeCommand;
    }

    private Set<CategoryCommand> convertCategories(Set<Category> categories) {
        return categories.stream()
                .map(categoryConverter::convert)
                .collect(toSet());
    }

    private Set<IngredientCommand> convertIngredients(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredientConverter::convert)
                .collect(toSet());
    }
}
