package ua.nure.silin.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/show/{id}")
    public String showRecipe(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }
}
