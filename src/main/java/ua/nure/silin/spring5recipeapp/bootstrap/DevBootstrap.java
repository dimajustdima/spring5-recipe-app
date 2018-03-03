package ua.nure.silin.spring5recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.domain.Ingredient;
import ua.nure.silin.spring5recipeapp.domain.Notes;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.domain.Recipe.RecipeBuilder;
import ua.nure.silin.spring5recipeapp.domain.UnitOfMeasure;
import ua.nure.silin.spring5recipeapp.repository.IngredientRepository;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;
import ua.nure.silin.spring5recipeapp.service.CategoryService;
import ua.nure.silin.spring5recipeapp.service.NotesService;
import ua.nure.silin.spring5recipeapp.service.UnitOfMeasureService;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static ua.nure.silin.spring5recipeapp.domain.Difficulty.EASY;
import static ua.nure.silin.spring5recipeapp.domain.Difficulty.MEDIUM;

@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryService categoryService;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureService unitOfMeasureService;
    private NotesService notesService;

    public DevBootstrap(CategoryService categoryService,
                        IngredientRepository ingredientRepository,
                        RecipeRepository recipeRepository,
                        UnitOfMeasureService unitOfMeasureService, NotesService notesService) {
        this.categoryService = categoryService;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureService = unitOfMeasureService;
        this.notesService = notesService;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeData();
    }

    private void initializeData() {
        List<UnitOfMeasure> measureUnits = unitOfMeasureService.getAllMeasureUnits();
        UnitOfMeasure unit = getUnit(measureUnits, "Unit");
        UnitOfMeasure teaspoon = getUnit(measureUnits, "Teaspoon");
        UnitOfMeasure tablespoon = getUnit(measureUnits, "Tablespoon");
        UnitOfMeasure cup = getUnit(measureUnits, "Cup");
        UnitOfMeasure dash = getUnit(measureUnits, "Dash");

        Category mexican = getCategory("mexican");
        Category vegan = getCategory("vegan");
        Category dinner = getCategory("dinner");
        Category grill = getCategory("grill");

        //Initialize guacamole here
        String guacamoleDirections = "1 Cut avocado, remove flesh\n" +
                "2 Mash with a fork\n" +
                "3 Add salt, lime juice, and the rest\n" +
                "4 Cover with plastic and chill to store";

        Ingredient avocado = new Ingredient("Avocado", new BigDecimal(2), unit);
        Ingredient kosherSalt = new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoon);
        Ingredient freshLimeJuice = new Ingredient("Fresh lime juice", new BigDecimal(1), tablespoon);
        Ingredient onion = new Ingredient("Onion", new BigDecimal(0.25), cup);
        Ingredient chiles = new Ingredient("Chiles", new BigDecimal(1), unit);
        Ingredient cilantro = new Ingredient("Cilantro", new BigDecimal(2), tablespoon);
        Ingredient blackPepper = new Ingredient("Black pepper", new BigDecimal(1), dash);
        Ingredient tomato = new Ingredient("Tomato", new BigDecimal(0.5), unit);

        Notes guacamoleNotes = new Notes("Be careful handling chiles if using. " +
                "Wash your hands thoroughly after handling and do not touch your eyes " +
                "or the area near your eyes with your hands for several hours.");

        Recipe guacamole = new RecipeBuilder("How to make perfect guacamole", 10, 0,
                4, "www.simplyrecipes.com", guacamoleDirections)
                .fromIngredients(avocado, kosherSalt, freshLimeJuice,
                        onion, chiles, cilantro, blackPepper, tomato)
                .ofDifficulty(EASY)
                .ofCategories(mexican, vegan)
                .withNotes(guacamoleNotes)
                .build();

        mexican.getRecipes().add(guacamole);
        vegan.getRecipes().add(guacamole);
        categoryService.saveAll(asList(mexican, vegan));

        recipeRepository.save(guacamole);

        guacamole.getIngredients().forEach(ingredient -> ingredient.setRecipe(guacamole));
        ingredientRepository.saveAll(guacamole.getIngredients());


        guacamoleNotes.setRecipe(guacamole);
        notesService.save(guacamoleNotes);


        //Initialize chicken taco here
        String chickenTacoDirections = "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken.\n" +
                "3 Grill the chicken.\n" +
                "4 Warm the tortillas.\n" +
                "5 Assemble the tacos.";

        Ingredient anchoChiliPowder = new Ingredient("Ancho chili powder", valueOf(2), tablespoon);
        Ingredient driedOregano = new Ingredient("Dried oregano", valueOf(1), teaspoon);
        Ingredient driedCummin = new Ingredient("Dried cummin", valueOf(1), teaspoon);
        Ingredient sugar = new Ingredient("Sugar", valueOf(1), teaspoon);
        Ingredient salt = new Ingredient("Salt", valueOf(0.5), teaspoon);
        Ingredient cloveGarlic = new Ingredient("Clove garlic", valueOf(1), teaspoon);
        Ingredient orangeZest = new Ingredient("Orange zest", valueOf(1), tablespoon);
        Ingredient orangeJuice = new Ingredient("Orange juice", valueOf(3), tablespoon);
        Ingredient oliveOil = new Ingredient("Olive oil", valueOf(2), tablespoon);
        Ingredient chickenThigs = new Ingredient("Chicken thighs", valueOf(5), unit);

        Notes chickenTacoNotes = new Notes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. " +
                "(If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder," +
                " though the flavor won't be quite the same.");

        Recipe chickenTaco = new RecipeBuilder("Spicy Grilled Chicken Tacos", 20, 15,
                5, "www.simplyrecipes.com", chickenTacoDirections)
                .fromIngredients(anchoChiliPowder, driedOregano, driedCummin, sugar, salt, cloveGarlic,
                        orangeZest, orangeJuice, oliveOil, chickenThigs)
                .ofDifficulty(MEDIUM)
                .ofCategories(dinner, grill)
                .withNotes(chickenTacoNotes)
                .build();
        dinner.getRecipes().add(chickenTaco);
        grill.getRecipes().add(chickenTaco);
        categoryService.saveAll(asList(dinner, grill));

        recipeRepository.save(chickenTaco);

        chickenTaco.getIngredients().forEach(ingredient -> ingredient.setRecipe(chickenTaco));
        ingredientRepository.saveAll(chickenTaco.getIngredients());


        chickenTacoNotes.setRecipe(chickenTaco);
        notesService.save(chickenTacoNotes);
    }

    private UnitOfMeasure getUnit(List<UnitOfMeasure> units, String description) {
        return unitOfMeasureService.getMeasureUnitFromListByName(units, description)
                .orElseThrow(() ->  new RuntimeException("Can't find measure unit of name: " + description));
    }

    private Category getCategory(String description) {
        return categoryService.getOrCreateCategory(description);
    }
}
