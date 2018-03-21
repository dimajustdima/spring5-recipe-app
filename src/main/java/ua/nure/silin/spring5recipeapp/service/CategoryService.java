package ua.nure.silin.spring5recipeapp.service;

import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.domain.Recipe;

public interface CategoryService {
    Category getOrCreateCategory(String description);
    Iterable<Category> saveAll(Iterable<Category> categories);
    Recipe removeCategoryForRecipe(Long recipeId, Long categoryId);
}
