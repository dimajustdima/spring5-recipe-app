package ua.nure.silin.spring5recipeapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.silin.spring5recipeapp.domain.Recipe;
import ua.nure.silin.spring5recipeapp.repository.RecipeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRecipeServiceTest {

    private static final Long RECIPE_ID = 1L;

    @InjectMocks
    private DefaultRecipeService defaultRecipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @Test
    public void shouldReturnRecipeOfParticularIdIfExists() {
        given(recipeRepository.findById(RECIPE_ID)).willReturn(Optional.of(new Recipe()));
        assertThat(defaultRecipeService.getRecipeById(RECIPE_ID)).isNotNull();
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfRecipeOfParticularIdDoesNotExist() {
        given(recipeRepository.findById(RECIPE_ID)).willReturn(Optional.empty());
        defaultRecipeService.getRecipeById(RECIPE_ID);
    }
}