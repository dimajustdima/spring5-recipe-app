package ua.nure.silin.spring5recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.silin.spring5recipeapp.command.IngredientCommand;
import ua.nure.silin.spring5recipeapp.command.RecipeCommand;
import ua.nure.silin.spring5recipeapp.command.UnitOfMeasureCommand;
import ua.nure.silin.spring5recipeapp.service.IngredientService;
import ua.nure.silin.spring5recipeapp.service.RecipeService;
import ua.nure.silin.spring5recipeapp.service.UnitOfMeasureService;

import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/recipe/{recipeId}")
public class IngredientController {

    private IngredientService ingredientService;
    private RecipeService recipeService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService,
                                RecipeService recipeService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(valueOf(recipeId));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/ingredientList";
    }

    @GetMapping("ingredient/new")
    public String showAddIngredient(@PathVariable String recipeId, Model model) {
        recipeService.getRecipeById(valueOf(recipeId));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.getAllMeasureUnits());

        return "recipe/ingredient/ingredientForm";
    }

    @GetMapping("ingredient/{id}/update")
    public String showUpdateIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
       IngredientCommand ingredientCommand =  ingredientService
               .getIngredientCommandByRecipeAndIngredientIds(valueOf(recipeId), valueOf(id));

       model.addAttribute("ingredient", ingredientCommand);
       model.addAttribute("uomList", unitOfMeasureService.getAllMeasureUnits());

       return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("ingredients")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId() + "/ingredients";
    }

    @GetMapping("ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
        ingredientService.deleteIngredient(valueOf(recipeId), valueOf(id));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
