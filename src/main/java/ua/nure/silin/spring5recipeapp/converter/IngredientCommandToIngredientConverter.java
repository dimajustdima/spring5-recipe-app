package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;

@Component
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter;

    public IngredientCommandToIngredientConverter(UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        Ingredient ingredient = new Ingredient(source.getDescription(), source.getAmount(),
                uomConverter.convert(source.getUom()));
        ingredient.setId(source.getId());
        return ingredient;
    }
}
