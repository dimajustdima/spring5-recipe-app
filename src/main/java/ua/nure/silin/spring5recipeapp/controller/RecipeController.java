package ua.nure.silin.spring5recipeapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.nure.silin.spring5recipeapp.command.RecipeCommand;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.exception.RecipeNotFoundException;
import ua.nure.silin.spring5recipeapp.service.RecipeService;

import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String showRecipe(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.getRecipeById(valueOf(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping("/new")
    public String showCreateRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("/{id}/update")
    public String showUpdateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeForm";
    }

    @PostMapping
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(valueOf(id));
        return "redirect:/";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRecipeNotFound(RecipeNotFoundException e, Model model) {
        model.addAttribute("exception", e);
        return "error/404";
    }
}
