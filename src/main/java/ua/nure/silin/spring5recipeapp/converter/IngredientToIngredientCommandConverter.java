package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;

@Component
public class IngredientToIngredientCommandConverter implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter;

    public IngredientToIngredientCommandConverter(UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setUom(uomConverter.convert(source.getUom()));
        return ingredientCommand;
    }
}
