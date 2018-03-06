package ua.nure.silin.spring5recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.service.RecipeService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    private static final String VIEW_NAME = "index";
    private static final String RECIPES_ATTRIBUTE = "recipes";

    private IndexController indexController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void shouldReturnNameOfTheView() {
        //when
        String viewName = indexController.getIndexPage(model);
        //then
        assertThat(viewName).isEqualTo(VIEW_NAME);
    }

    @Test
    public void shouldRetrieveListOfAvailableRecipes() {
        //when
        indexController.getIndexPage(model);
        //then
        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    public void shouldSetRetrievedListOfAvailableRecipesToTheModel() {
        //given
        List<Recipe> recipes = Collections.emptyList();
        given(recipeService.getAllRecipes()).willReturn(recipes);

        //when
        indexController.getIndexPage(model);

        //then
        verify(model, times(1)).addAttribute(RECIPES_ATTRIBUTE, recipes);
    }

}