package ua.nure.silin.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.silin.spring5recipeapp.service.CategoryService;

import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/recipe/{recipeId}/category")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{categoryId}/remove")
    public String removeCategoryForRecipe(@PathVariable String recipeId, @PathVariable String categoryId) {
        categoryService.removeCategoryForRecipe(valueOf(recipeId), valueOf(categoryId));
        return "redirect:/recipe/" + recipeId + "/update";
    }
}
