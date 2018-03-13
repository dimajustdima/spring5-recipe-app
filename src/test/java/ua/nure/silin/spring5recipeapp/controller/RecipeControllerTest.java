package ua.nure.silin.spring5recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.nure.silin.spring5recipeapp.service.RecipeService;

@RunWith(SpringRunner.class)
public class RecipeControllerTest {

    private static final String REQUEST_URL = "/recipe/1/show";
    private static final String VIEW_NAME = "recipe/show";

    private MockMvc mockMvc;
    private RecipeController recipeController;
    @MockBean
    private RecipeService recipeService;

    @Before
    public void setUp() {
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void shouldReturnRecipeViewOnGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(VIEW_NAME));
    }

}