package ua.nure.silin.spring5recipeapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.nure.silin.spring5recipeapp.domain.Category;
import ua.nure.silin.spring5recipeapp.repository.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategoryServiceTest {

    @InjectMocks
    private DefaultCategoryService defaultCategoryService;
    @Mock
    private CategoryRepository categoryRepository;


    @Test
    public void shouldReturnCurrentCategoryOfTheGivenNameIfExistsIgnoringCase() {
        //given
        Category existingCategory = new Category("Mexican");
        existingCategory.setId(1L);
        given(categoryRepository.findByDescriptionIgnoreCase("mexican")).willReturn(Optional.of(existingCategory));
        //when
        Category retrievedCategory = defaultCategoryService.getOrCreateCategory("mexican");
        //then
        assertThat(retrievedCategory).isSameAs(existingCategory);
    }

    @Test
    public void shouldCreateNewCategoryOfTheGivenNameIfDoesNotExistCapitalizingName() {
        //given
        given(categoryRepository.findByDescriptionIgnoreCase("mexican")).willReturn(Optional.empty());
        //when
        Category retrievedCategory = defaultCategoryService.getOrCreateCategory("mexican");
        //then
        assertThat(retrievedCategory.getId()).isNull();
        assertThat(retrievedCategory.getDescription()).isEqualTo("Mexican");
    }

}