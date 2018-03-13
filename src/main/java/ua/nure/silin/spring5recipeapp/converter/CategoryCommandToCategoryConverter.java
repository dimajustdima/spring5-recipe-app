package ua.nure.silin.spring5recipeapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.command.CategoryCommand;
import ua.nure.silin.spring5recipeapp.domain.Category;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand, Category> {

    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        Category category = new Category(source.getDescription());
        category.setId(source.getId());
        return category;
    }
}
