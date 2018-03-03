package ua.nure.silin.spring5recipeapp.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.repository.CategoryRepository;

@Service
public class DefaultCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getOrCreateCategory(String description) {
        return categoryRepository.findByDescriptionIgnoreCase(description)
                .orElseGet(() -> new Category(StringUtils.capitalize(description)));
    }

    @Override
    public Iterable<Category> saveAll(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}
