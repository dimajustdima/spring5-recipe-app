package ua.nure.silin.spring5recipeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.CategoryRepository;

import static java.lang.String.format;

@Service
@Slf4j
public class DefaultCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;
    private RecipeService recipeService;

    public DefaultCategoryService(CategoryRepository categoryRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
    }

    @Override
    public Category getOrCreateCategory(String description) {
        return categoryRepository.findByDescriptionIgnoreCase(description)
                .orElseGet(() -> {
                    log.warn(format("Category %s not found. Creating category.", description));
                    return new Category(StringUtils.capitalize(description));
                });
    }

    @Override
    public Iterable<Category> saveAll(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Recipe removeCategoryForRecipe(Long recipeId, Long categoryId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        recipe.getCategories().removeIf(category -> category.getId().equals(categoryId));
        return recipeService.save(recipe);
    }
}
