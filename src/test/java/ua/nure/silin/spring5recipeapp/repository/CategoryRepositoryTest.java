package ua.nure.silin.spring5recipeapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.nure.silin.spring5recipeapp.domain.Category;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldFindCategoryByDescriptionIgnoringCase() {
        //given
        testEntityManager.persist(new Category("Test category"));
        //when
        Optional<Category> categoryOpt = categoryRepository.findByDescriptionIgnoreCase("test Category");
        //then
        assertThat(categoryOpt).isPresent();
        assertThat(categoryOpt.get().getDescription()).isEqualTo("Test category");
        assertThat(categoryOpt.get().getId()).isNotNull();
    }

}