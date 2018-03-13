package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.UnitOfMeasureCommand;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureCommandToUnitOfMeasureConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure(source.getDescription());
        unitOfMeasure.setId(source.getId());
        return unitOfMeasure;
    }
}
