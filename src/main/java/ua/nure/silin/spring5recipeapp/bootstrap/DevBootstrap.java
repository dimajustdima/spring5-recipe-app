package ua.nure.silin.spring5recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.domain.Difficulty;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;
import ua.nure.silin.spring5recipeapp.domain.Notes;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.domain.Recipe.RecipeBuilder;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;
import ua.nure.silin.spring5recipeapp.repository.CategoryRepository;
import ua.nure.silin.spring5recipeapp.repository.IngredientRepository;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;
import ua.nure.silin.spring5recipeapp.service.UnitOfMeasureService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static ua.nure.silin.spring5recipeapp.domain.Difficulty.EASY;

@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureService unitOfMeasureService;

    public DevBootstrap(CategoryRepository categoryRepository,
                        IngredientRepository ingredientRepository,
                        RecipeRepository recipeRepository,
                        UnitOfMeasureService unitOfMeasureService) {
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeData();
    }

    private void initializeData() {
        List<UnitOfMeasure> measureUnits = unitOfMeasureService.getAllMeasureUnits();

        //Initialize guacamole here
        Category mexican = new Category("mexican");
        Category vegan = new Category("vegan");

        Ingredient avocado = new Ingredient("Avocado", 2, getUnit(measureUnits, ""));

        Recipe guacamole = new RecipeBuilder("How to make perfect guacamole", 10, 0,
                4, "www.simplyrecipes.com",
                "1 Cut avocado, remove flesh\n" +
                        "2 Mash with a fork\n" +
                        "3 Add salt, lime juice, and the rest\n" +
                        "4 Cover with plastic and chill to store")
                .fromIngredients(new HashSet<>(asList(new Ingredient("avocado"))))
                .ofDifficulty(EASY)
                .ofCategories(new HashSet<>(asList(mexican, vegan)))
                .withNotes(new Notes("Be careful handling chiles if using. " +
                        "Wash your hands thoroughly after handling and do not touch your eyes " +
                        "or the area near your eyes with your hands for several hours."))
                .build();
    }

    private Set<Ingredient> prepareIngredients() {
    }

    private Ingredient prepareIngredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(description);
        ingredient.setAmount(amount);
        ingredient.setUom(uom);
        return ingredient;
    }

    private UnitOfMeasure getUnit(List<UnitOfMeasure> units, String description) {
        return unitOfMeasureService.getMeasureUnitFromListByName(units, description)
                .orElseThrow(() ->  new RuntimeException("Can't find measure unit of name: " + description));
    }
}
