package ua.nure.silin.spring5recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.silin.spring5recipeapp.domain.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {
}
