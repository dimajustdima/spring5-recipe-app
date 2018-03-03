package ua.nure.silin.spring5recipeapp.service;

import ua.nure.silin.spring5recipeapp.domain.Category;

public interface CategoryService {
    Category getOrCreateCategory(String description);
    Iterable<Category> saveAll(Iterable<Category> categories);
}
